package com.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.dto.AdmDTO;
import com.shop.dto.ItemDTO;
import com.shop.mapper.ItemMapper;
import com.shop.service.AdmService;

@Controller
public class MainController {

	@Autowired
	AdmService service;
	
	@Autowired
	ItemMapper mapper;
	
	@Value("${admindir}")
	String admindir;
	
	@Value("${custdir}")
	String custdir;
	
	
	@RequestMapping("/")
	public String main() {
		return "index";
	}
	
	@RequestMapping("/ocrtest")
	public String ocrtest(Model model) {
		model.addAttribute("center", "ocr");
		return "index";
	}
	
	@RequestMapping("/websocket")
	public String websocket(Model model) {
		model.addAttribute("center", "websocket");
		return "index";
	}

	
	
	@RequestMapping("/searchimpl")
	public String searchimpl(Model model, String txt) {
		List<ItemDTO> list = null;
		try {
			list = mapper.searchitem(txt);
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("center", "search");
		return "index";
	}
	
	@RequestMapping("/loginimpl")
	public String loginimpl(HttpSession session, String id, String pwd) {
		AdmDTO adm = null;
		try {
			adm = service.get(id);
			if(adm != null) {
				if(adm.getPwd().equals(pwd)) {
					session.setAttribute("loginadmin", adm);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
}









