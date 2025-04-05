package com.group1.vipbilliardspayment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserTest {
	@GetMapping("/ADDMH")
	public String themMatHang()
	{
		return "themmathang";
	}
}
