/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.solr.cluster.placement.plugins;

import org.apache.solr.cluster.placement.PlacementPluginConfig;
import org.apache.solr.common.annotation.JsonProperty;

/**
 * Configuration bean for {@link AffinityPlacementFactory}.
 */
public class AffinityPlacementConfig implements PlacementPluginConfig {

  public static final String COLLECTION_NODE_TYPE_PROPERTY = "placement.affinity.node_type";
  public static final String WITH_COLLECTION_PROPERTY = "placement.affinity.withCollection";

  public static final long DEFAULT_MINIMAL_FREE_DISK_GB = 20L;
  public static final long DEFAULT_PRIORITIZED_FREE_DISK_GB = 100L;

  public static final AffinityPlacementConfig DEFAULT =
      new AffinityPlacementConfig(DEFAULT_MINIMAL_FREE_DISK_GB, DEFAULT_PRIORITIZED_FREE_DISK_GB);

  /**
   * If a node has strictly less GB of free disk than this value, the node is excluded from assignment decisions.
   * Set to 0 or less to disable.
   */
  @JsonProperty
  public long minimalFreeDiskGB;

  /**
   * Replica allocation will assign replicas to nodes with at least this number of GB of free disk space regardless
   * of the number of cores on these nodes rather than assigning replicas to nodes with less than this amount of free
   * disk space if that's an option (if that's not an option, replicas can still be assigned to nodes with less than this
   * amount of free space).
   */
  @JsonProperty
  public long prioritizedFreeDiskGB;

  /**
   * Zero-arguments public constructor required for deserialization - don't use.
   */
  public AffinityPlacementConfig() {
    this(DEFAULT_MINIMAL_FREE_DISK_GB, DEFAULT_PRIORITIZED_FREE_DISK_GB);
  }

  /**
   * Configuration for the {@link AffinityPlacementFactory}.
   * @param minimalFreeDiskGB minimal free disk GB.
   * @param prioritizedFreeDiskGB prioritized free disk GB.
   */
  public AffinityPlacementConfig(long minimalFreeDiskGB, long prioritizedFreeDiskGB) {
    this.minimalFreeDiskGB = minimalFreeDiskGB;
    this.prioritizedFreeDiskGB = prioritizedFreeDiskGB;
  }
}
