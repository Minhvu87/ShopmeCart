package com.shopme.common.entity;

public enum ArticleType {

	MENU_BOUND {
		public String toString() { return "Menu-Bound"; }
	},
	
	FREE {
		public String toString() { return "Free"; }
	}
}
