package com.group1.vipbilliardspayment.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity

@Table(name = "BanBida")
public class BanBida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SoBan")
    private Integer soBan;

    @Column(name = "TrangThai")
    private Integer trangThai;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loaiBan")
    LoaiBan loaiBan;
	public Integer getSoBan() {
		return soBan;
	}

	public BanBida() {
		super();
	}

	public BanBida(Integer trangThai, LoaiBan loaiBan) {
		super();
		this.trangThai = trangThai;
		this.loaiBan = loaiBan;
	}

	public void setSoBan(int soBan) {
		this.soBan = soBan;
	}

	
	public void setTrangThai(Integer trangThai) {
		this.trangThai = trangThai;
	}

	public Integer getTrangThai() {
		return trangThai;
	}

	

	public LoaiBan getLoaiBan() {
		return loaiBan;
	}

	public void setLoaiBan(LoaiBan loaiBan) {
		this.loaiBan = loaiBan;
	}

	public void setSoBan(Integer soBan) {
		this.soBan = soBan;
	}

    
}