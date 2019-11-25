package com.lym.spring.framework.beans;

import java.util.ArrayList;
import java.util.List;

public class ConstructorArgment {
	
	private List<ValueHolder> valueHolders = new ArrayList<>();
	
	public ConstructorArgment() {}
	
	public List<ValueHolder> getValueHolders() {
		return valueHolders;
	}

	public void addArgumentValue(Object value) {
		this.valueHolders.add(new ValueHolder(value));
	}
	
	public void addArgumentValue(ValueHolder valueHolder) {
		this.valueHolders.add(valueHolder);
	}
	
	public int getArgumentCount() {
		return this.valueHolders.size();
	}
	
	public boolean isEmpty() {
		return this.valueHolders.isEmpty();
	}
	
	public class ValueHolder {
		private Object value;

		private String type;

		private String name;

		public ValueHolder(Object value) {
			this.value = value;
		}

		public ValueHolder(Object value, String type) {
			this.value = value;
			this.type = type;
		}

		public ValueHolder(Object value, String type, String name) {
			this.value = value;
			this.type = type;
			this.name = name;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		
	}
}
