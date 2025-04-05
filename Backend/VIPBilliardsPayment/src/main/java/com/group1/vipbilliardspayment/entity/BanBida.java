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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity

@Table(name = "BanBida")

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BanBida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SoBan")
    Integer soBan;

    @Column(name = "TrangThai")
    Integer trangThai;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loaiBan")
    LoaiBan loaiBan;
}