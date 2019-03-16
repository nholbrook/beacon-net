package com.nickholbrook.beaconnet.model;

import java.util.Comparator;

public class EntryComparator implements Comparator<Entry> {

	/**
	 * <p>
	 * Compare:C
	 * <p>
	 * <ol>
	 *   <li>Genre</li>
	 *   <li>Author</li>
	 *   <li>Title</li>
	 * </ol>
	 * <p>
	 * Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or
	 * greater than the second.
	 * </p>
	 */
	@Override
	public int compare(Entry o1, Entry o2) {
		/*int compareRslt = o1.getMacAddress().compareTo(o2.getMacAddress());
		if (compareRslt == 0) {
			compareRslt = o1.getLastTime().compareToIgnoreCase( o2.getLastTime());
			if (compareRslt == 0) {
				compareRslt = o1.getFirstTime().compareToIgnoreCase( o2.getFirstTime());
			}
		}
		return compareRslt;*/
		return 0;
	}
}
