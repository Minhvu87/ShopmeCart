package com.shopme.admin.section;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.common.entity.section.Section;
import com.shopme.common.exception.SectionNotFoundException;

@Controller
public class GeneralSectionController {

    @Autowired private SectionService service;
	
	@GetMapping("/sections")
	public String listAllSections(Model model) {
		List<Section> listSections = service.listSections();
		model.addAttribute("listSections", listSections);
		
		return "sections/sections";
	}

	@GetMapping("/sections/delete/{id}")
	public String deleteSection(@PathVariable(name = "id") Integer id, RedirectAttributes ra) {
		try {
			service.deleteSection(id);
			ra.addFlashAttribute("message", "The section ID " + id + " has been deleted.");
			
		} catch (SectionNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
		}
		
		return "redirect:/sections";		
	}
	
	@GetMapping("/sections/{id}/enabled/{enabledStatus}")
	public String updateSectionEnabledStatus(@PathVariable(name = "id") Integer id, @PathVariable("enabledStatus") String enabledStatus,
			RedirectAttributes ra) {
		try {
			boolean enabled = Boolean.parseBoolean(enabledStatus);
			service.updateSectionEnabledStatus(id, enabled);
			String updateResult = enabled ? "enabled." : "disabled.";
			ra.addFlashAttribute("message", "The section ID " + id + " has been " + updateResult);
			
		} catch (SectionNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
		}
		
		return "redirect:/sections";
	}
	
	@GetMapping("/sections/moveup/{id}")
	public String moveSectionUp(@PathVariable(name = "id") Integer id, RedirectAttributes ra) {
		try {
			service.moveSectionUp(id);
			
			ra.addFlashAttribute("message", "The section ID " + id + " has been moved up by one position.");
			
		} catch (SectionUnmoveableException | SectionNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
		}
		
		return "redirect:/sections";		
	}
	
	@GetMapping("/sections/movedown/{id}")
	public String moveSectionDown(@PathVariable(name = "id") Integer id, RedirectAttributes ra) {
		try {
			service.moveSectionDown(id);
			ra.addFlashAttribute("message", "The section ID " + id + " has been moved down by one position.");
			
		} catch (SectionUnmoveableException | SectionNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
		}
		
		return "redirect:/sections";		
	}
}
