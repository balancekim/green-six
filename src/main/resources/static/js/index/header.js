/**
 * 
 */
	$(function() {
		var li = $("#gnb > .wrap > ul > li");

		// Use mouseenter and mouseleave for the main menu items
		li.mouseenter(function() {
			var index = li.index(this) + 1;
			showSubMenu(index);
		});

		li.mouseleave(function() {
			var index = li.index(this) + 1;
			hideSubMenu(index);
		});

	});

	function showSubMenu(index) {
		$("#gnb > .wrap > ul > li:nth-of-type(" + index + ") > div").show()
	}

	function hideSubMenu(index) {
		$("#gnb > .wrap > ul > li:nth-of-type(" + index + ") > div").hide()
	}