// This file is an automatically generated file, please do not edit this file, modify the WrapperGenerator.java file instead !

package sun.awt.X11;

import jdk.internal.misc.Unsafe;

import sun.util.logging.PlatformLogger;
public class XFontSetExtents extends XWrapperBase { 
	private Unsafe unsafe = XlibWrapper.unsafe; 
	private final boolean should_free_memory;
	public static int getSize() { return 16; }
	public int getDataSize() { return getSize(); }

	long pData;

	public long getPData() { return pData; }


	public XFontSetExtents(long addr) {
		log.finest("Creating");
		pData=addr;
		should_free_memory = false;
	}


	public XFontSetExtents() {
		log.finest("Creating");
		pData = unsafe.allocateMemory(getSize());
		should_free_memory = true;
	}


	public void dispose() {
		log.finest("Disposing");
		if (should_free_memory) {
			log.finest("freeing memory");
			unsafe.freeMemory(pData); 
	}
		}
	public XRectangle get_max_ink_extent() { log.finest("");return new XRectangle(pData + 0); }
	public XRectangle get_max_logical_extent() { log.finest("");return new XRectangle(pData + 8); }


	String getName() {
		return "XFontSetExtents"; 
	}


	String getFieldsAsString() {
		StringBuilder ret = new StringBuilder(80);

		ret.append("max_ink_extent = ").append( get_max_ink_extent() ).append(", ");
		ret.append("max_logical_extent = ").append( get_max_logical_extent() ).append(", ");
		return ret.toString();
	}


}



