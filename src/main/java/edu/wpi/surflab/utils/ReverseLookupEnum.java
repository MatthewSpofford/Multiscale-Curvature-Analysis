package edu.wpi.surflab.utils;

/**
 * Intended for reverse lookup C-like enums (values can only occur once).
 * @param <K> Value of the enum being looked up.
 * @param <V> Output of the enum that was found
 *
 * @author Matthew Spofford
 */
public interface ReverseLookupEnum<K, V> {

  /**
   * Lookup C-like enums corresponding to given value.
   * @param value Value of the enum being looked up.
   * @return Output of the enum that was found.
   */
  V lookup(K value);
}
