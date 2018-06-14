package com.wangguowei.properties;

import java.util.Map;

public class MyHashMap {
	static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry next;
		private int hash;
		
		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}

		@Override
		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

	}

}
