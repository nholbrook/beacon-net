package com.nickholbrook.beaconnet.controllers;

import java.util.List;

import com.nickholbrook.beaconnet.model.EntryInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EntrySearchController extends EntryControllerBase {
	private static final Log logger = LogFactory.getLog(EntrySearchController.class);

	/**
	 * <p>
	 * Search by the entry mac address and last time.
	 * </p>
	 * @param macAddress entry mac address
	 * @param lastTime entry last time
	 * @param redirect Spring redirect attribute used to return the search result (as a flash attribute).
	 * @return the redirect page.
	 */
	@RequestMapping(value = "/macaddress-lasttime-search", method = RequestMethod.POST)
	public String searchByMacAddressLastTime(@RequestParam("macAddress")  String macAddress,
			@RequestParam("lastTime") String lastTime,
			RedirectAttributes redirect) {
		if (macAddress != null && macAddress.length() > 0) {
			if (lastTime != null && lastTime.length() > 0) {
				logger.info("searchByTitleAuthor: macAddress = " + macAddress + ", lastTime = " + lastTime);
				List<EntryInfo> entryInfoList = getEntryTableService().findBookByTitleAuthor(lastTime, macAddress);
				if (entryInfoList != null && entryInfoList.size() > 0) {
					redirect.addFlashAttribute(ENTRY_LIST, entryInfoList);
				}
			} else {
				redirect.addFlashAttribute("macAddress_lastTime_lastTime_error", "Last Time must be specified");
			}
		} else {
			redirect.addFlashAttribute("macAddress_lastTime_macAddress_error", "Mac Address must be specified");
		}

		return "redirect:/";
	}

	/**
	 * <p>
	 * Search by the entry mac address.
	 * </p>
	 * @param macAddress Entry mac address
	 * @param redirect Spring redirect object used to return the search result.
	 * @return the redirect page.
	 */
	@RequestMapping(value = "/macaddress-search", method = RequestMethod.POST)
	public String searchByMacAddress(@RequestParam("macAddress") String macAddress, RedirectAttributes redirect) {
		if (macAddress != null && macAddress.length() > 0) {
			List<EntryInfo> bookInfoList = getEntryTableService().findBookByAuthor(macAddress);
			if (bookInfoList != null && bookInfoList.size() > 0) {
				redirect.addFlashAttribute(ENTRY_LIST, bookInfoList);
			}
		}
		return "redirect:/";
	}

	/**
	 * <p>
	 * Search by the entry last time
	 * </p>
	 *
	 * @param lastTime The title substring to search book titles for
	 * @param redirect the RedirectAttributes object used to return the search result
	 * @return the redirect page
	 */
	@RequestMapping(value = "/lasttime-search", method = RequestMethod.POST)
	public String searchByTitle(@RequestParam("lastTime") String lastTime, RedirectAttributes redirect) {
		if (lastTime != null && lastTime.length() > 0) {
			List<EntryInfo> entryInfoList = getEntryTableService().findBookByTitle(lastTime);
			if (entryInfoList != null && entryInfoList.size() > 0) {
				redirect.addFlashAttribute(ENTRY_LIST, entryInfoList);
			}
		}
		return "redirect:/raw";
	}

	/**
	 * <p>
	 * Return all books in the database
	 * </p>
	 *
	 * @param redirect
	 * @return
	 */
	@RequestMapping(value = "/list-all-entrys", method = RequestMethod.POST)
	public String getAllBooks( RedirectAttributes redirect ) {
		List<EntryInfo> entryInfoList = getEntryTableService().getBooks();
		if (entryInfoList != null && entryInfoList.size() > 0) {
			redirect.addFlashAttribute(ENTRY_LIST, entryInfoList);
		}
		return "redirect:/raw";
	}
}
