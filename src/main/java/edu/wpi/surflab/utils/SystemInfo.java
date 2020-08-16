package edu.wpi.surflab.utils;

import com.sun.javafx.util.Utils;
import java.util.concurrent.Callable;
import lombok.Getter;

/**
 * Maintains easy to retrieve system and machine related info.
 */
public final class SystemInfo {

  /**
   * Maintains type of operating system.
   * A given function is then used later to check if the operating system enum is actually being
   * used.
   */
  public enum OSType implements UsageCheckableEnum {
    WIN(Utils::isWindows),
    MAC(Utils::isMac),
    LINUX(Utils::isUnix),
    UNKNOWN(null);

    /**
     * Creates OSType enum. Uses a given check function later to determine OS.
     * @param checkFunction Function used later to check if the operating system is actually being
     *                      used. If function is null, then type is UNKNOWN.
     */
    OSType(Callable<Boolean> checkFunction) {
      this.checkFunction = checkFunction;
    }
    private final Callable<Boolean> checkFunction;

    @Override
    public boolean checkUsed() {
      try {
        return checkFunction.call();
      } catch (Exception e) {
        return false;
      }
    }
  }

  /**
   * Maintains type of system architecture.
   * String corresponds to architecture type.
   */
  public enum ArchType implements UsageCheckableEnum {
    x64("64"),
    x86("32"),
    UNKNOWN(null);

    /**
     * Creates ArchType enum. Input corresponds to system architecture property.
     * @param archProperty String corresponds to sun.arch.data.model property output.
     */
    ArchType(String archProperty) {
      this.archProperty = archProperty;
    }
    private final String archProperty;

    @Override
    public boolean checkUsed() {
      return archProperty.equals(System.getProperty("sun.arch.data.model"));
    }
  }

  /**
   * Stores currently running operating system.
   */
  @Getter private static final OSType currOS;
  /**
   * Stores currently running operating system.
   */
  @Getter private static final ArchType currArch;

  static {
    currOS = UsageCheckableEnum.getUsed(OSType.values(), OSType.UNKNOWN);
    currArch = UsageCheckableEnum.getUsed(ArchType.values(), ArchType.UNKNOWN);
  }
}
