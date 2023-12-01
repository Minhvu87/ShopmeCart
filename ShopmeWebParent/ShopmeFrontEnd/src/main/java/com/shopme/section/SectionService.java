package com.shopme.section;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.section.Section;

@Service
public class SectionService {

    @Autowired private SectionRepository repo;
	
	public List<Section> listEnabledSections() {
		return repo.findAllByEnabledOrderBySectionOrderAsc(true);
	}
}
