package bond.system

import bond.config.AppConfig

import javax.inject.Inject

/**
 * This service holds information about the app infrastructure
 * such as the current version, operating system...
 *
 * @since 0.1.0
 */
class SystemService {

  @Inject
  AppConfig config

  /**
   * Returns the current OS
   *
   * @since 0.1.0
   * @return a {@link String} containing operating system id
   */
  String getSystemOS() {
    System.getProperty('os.name').toLowerCase()
  }

  /**
   * Returns the current system's version
   *
   * @return the current system's version
   */
  String getSystemVersion() {
    config.version
  }

  BigInteger getTotalMemory() {
    Runtime.getRuntime().totalMemory()
  }

  BigInteger getAvailableMemory() {
    Runtime.getRuntime().freeMemory()
  }

  BigInteger getUsedMemory() {
    return totalMemory - availableMemory
  }
}
