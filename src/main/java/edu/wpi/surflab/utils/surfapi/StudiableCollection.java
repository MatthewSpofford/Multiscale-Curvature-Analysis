package edu.wpi.surflab.utils.surfapi;

import edu.wpi.surflab.utils.surfapi.types.StudiableInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Stores the collective data for surface. Contains general data metadata ({@link
 * edu.wpi.surflab.utils.surfapi.types.StudiableInfo}), as well as point arrays, and comments.
 *
 * @author Matthew Spofford
 */
@AllArgsConstructor
public class StudiableCollection {

  /** Maintains metadata/info about the surface. */
  @Getter private final StudiableInfo metadata;
  /** Comment corresponding to studiable. */
  @Getter private final String comment;
  /** Points corresponding to studiable. Columns */
  @Getter private final int[] points;
}
