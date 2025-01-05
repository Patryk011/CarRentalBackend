package org.example.carrent.service;

import org.example.carrent.Utils.MoneyUtil;
import org.example.carrent.dto.PaymentDTO;
import org.example.carrent.dto.RentalDTO;
import org.example.carrent.entity.Rental;
import org.example.carrent.enums.RentalStatus;
import org.example.carrent.exception.ResourceNotFoundException;
import org.example.carrent.mapper.RentalMapper;
import org.example.carrent.payuConfiguration.PaymentCreationResponse;
import org.example.carrent.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final PaymentService paymentService;

    public RentalServiceImpl(RentalRepository rentalRepository,
                             RentalMapper rentalMapper,
                             PaymentService paymentService) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
        this.paymentService = paymentService;
    }


    @Override
    public RentalDTO addRental(RentalDTO rentalDTO) {
        Rental rental = rentalMapper.toEntity(rentalDTO);


        long days = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getFinishDate());
        BigDecimal pricePerDay = MoneyUtil.toDisplayFormat(BigDecimal.valueOf(rental.getCar().getPricePerDay()));
        BigDecimal totalCost = MoneyUtil.calculateTotalCost(pricePerDay, days);

        Integer discountPercentage = rental.getCustomer().getDiscountPercentage();

        if (discountPercentage != null) {
            BigDecimal discount = totalCost.multiply(BigDecimal.valueOf(discountPercentage))
                    .divide(BigDecimal.valueOf(100));
            totalCost = totalCost.subtract(discount);
        }

        rental.setStatus(RentalStatus.CONFIRMED);
        rental.setTotalCost(totalCost);


        rental = rentalRepository.save(rental);

        return rentalMapper.toDto(rental);
    }

    @Override
    public RentalDTO findRentalById(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rental with id " + id + " not found"));
        return rentalMapper.toDto(rental);
    }

    @Override
    public List<RentalDTO> getAllRentals() {
        return rentalMapper.toDto(rentalRepository.findAll());
    }

    @Override
    public RentalDTO cancelRental(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rental with id " + id + " not found"));
        if (rental.getStatus() == RentalStatus.FINISHED) {
            throw new IllegalStateException("Cannot cancel a finished rental.");
        }
        rental.setStatus(RentalStatus.CANCELLED);
        rentalRepository.save(rental);
        return rentalMapper.toDto(rental);
    }


}
