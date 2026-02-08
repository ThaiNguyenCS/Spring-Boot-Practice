package com.example.learning.db_lock.service;

import com.example.learning.db_lock.entity.Voucher;
import com.example.learning.db_lock.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoucherService {
    private final VoucherRepository voucherRepository;
    public boolean claimVoucher(Long voucherId) throws InterruptedException{
        Optional<Voucher> voucherOpt = voucherRepository.findVoucherById(voucherId);
        if(voucherOpt.isEmpty())
        {
            throw new RuntimeException(String.format("Voucher {} does not exist", voucherId));
        }
        Voucher voucher = voucherOpt.get();
        if(voucher.getClaimCount() < voucher.getMaxQuantity())
        {
            Thread.sleep(500); // giả lập logic nặng
            voucher.setClaimCount(voucher.getClaimCount() + 1);
            voucherRepository.save(voucher);
            return true;
        }
        throw new RuntimeException("Voucher is out of stock");
    }
}
