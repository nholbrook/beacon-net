package com.nickholbrook.beaconnet.api;

import java.util.List;

import com.nickholbrook.beaconnet.model.Entry;
import com.nickholbrook.beaconnet.service.EntryTableService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryAPI {
  private final static EntryTableService entryTableService = new EntryTableService();
  private static final String ENTRY_LIST = "entryList";
  private EntryTableService getEntryTableService() {
    return entryTableService;
  }

  @RequestMapping(value = "/entries", method = RequestMethod.GET)
  @ResponseBody
  public List<Entry> getAllEntries() {
    List<Entry> entryList = getEntryTableService().getEntries();
    return entryList;
  }

}
