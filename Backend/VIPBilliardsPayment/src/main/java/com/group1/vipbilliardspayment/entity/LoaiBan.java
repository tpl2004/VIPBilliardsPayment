package com.group1.vipbilliardspayment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "LoaiBan")

public class LoaiBan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LoaiBan")
    private Integer loaiBan;

    @Column(name = "TenLoai", length = 100)
    private String tenLoai;

    @Column(name = "DonGia")
    private Double donGia;

	public Integer getLoaiBan() {
		return loaiBan;
	}

	public void setLoaiBan(Integer loaiBan) {
		this.loaiBan = loaiBan;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public Double getDonGia() {
		return donGia;
	}

	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}

	public LoaiBan() {
		super();
	}

	public LoaiBan( String tenLoai, Double donGia) {
		super();
		
		this.tenLoai = tenLoai;
		this.donGia = donGia;
	}
    
}
