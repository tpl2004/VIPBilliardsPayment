package com.group1.vipbilliardspayment.repository;

import com.group1.vipbilliardspayment.dto.request.ThongKeDoanhThuTheoNgayRequest;
import com.group1.vipbilliardspayment.dto.response.ThongKeDoanhThuTheoNgayResponse;
import com.group1.vipbilliardspayment.entity.BanBida;
import com.group1.vipbilliardspayment.entity.HoiVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group1.vipbilliardspayment.entity.HoaDon;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    public boolean existsByMaHoaDon(Integer maHoaDon);

    @Query("SELECT e FROM HoaDon e WHERE CAST(e.thoiDiemVao as DATE) = :createdDate")
    public List<HoaDon> findByDate(@Param("createdDate") Date createdDate);

    public List<HoaDon> findByBanBida(BanBida banBida);

    @Query(
        value = "exec proc_ThongKeDoanhThuTheoNgay @NgayBatDau = :NgayBatDau, @NgayKetThuc = :NgayKetThuc",
        nativeQuery = true
    )
    public List<Object[]> thongKeDoanhThuTheoNgay(@Param("NgayBatDau") Date ngayBatDau, @Param("NgayKetThuc") Date ngayKetThuc);
}
