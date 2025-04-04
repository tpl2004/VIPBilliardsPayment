package com.group1.vipbilliardspayment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "MatHang")

public class MatHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHang")
    private Integer maHang;

    @Column(name = "TenHang", length = 100)
    private String tenHang;

    @Column(name = "DonGia")
    private Double donGia;

	public MatHang( String tenHang, Double donGia) {
		super();
		
		this.tenHang = tenHang;
		this.donGia = donGia;
	}

	public MatHang() {
		super();
	}

	public int getMaHang() {
		return maHang;
	}

	public void setMaHang(int maHang) {
		this.maHang = maHang;
	}

	public String getTenHang() {
		return tenHang;
	}

	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}

	public Double getDonGia() {
		return donGia;
	}

	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}
    
    
}
