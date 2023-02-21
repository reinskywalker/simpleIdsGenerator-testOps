package config

import internal.GlobalVariable

import org.checkerframework.common.reflection.qual.GetClass

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.thoughtworks.selenium.webdriven.commands.GetXpathCount

import config.Cases.SelectorType

public class Cases {

	enum SelectorType {
		XPATH("xpath"),
		CSS("css"),
		ATTRIBUTE("attribute"),
		NAME("name"),
		CLASS("class"),
		ID("id"),
		VALUE("value"),
		TITLE("title")

		String value;

		public SelectorType(String value) {
			this.value = value
		}
	}

	public static TestObject getValueTestObject(String selectorValue) {
		return getTestObject(SelectorType.VALUE, selectorValue)
	}

	public static TestObject getIdTestObject(String selectorValue) {
		return getTestObject(SelectorType.ID, selectorValue)
	}

	public static TestObject getTitleTestObject(String selectorValue) {
		return getTestObject(SelectorType.TITLE, selectorValue)
	}

	public static TestObject getClassTestObject(String selectorValue) {
		return getTestObject(SelectorType.CLASS, selectorValue)
	}

	public static TestObject getNameTestObject(String selectorValue) {
		return getTestObject(SelectorType.NAME, selectorValue)
	}

	public static TestObject getXpathTestObject(String selectorValue) {
		return getTestObject(SelectorType.XPATH, selectorValue)
	}

	public static TestObject getCSSTestObject(String selectorValue){
		return getTestObject(SelectorType.CSS, selectorValue)
	}

	private static TestObject getTestObject(SelectorType selectorType, String selectorValue) {
		TestObject to = new TestObject()
		to.addProperty(selectorType.value, ConditionType.EQUALS, selectorValue)
		return to
	}
}