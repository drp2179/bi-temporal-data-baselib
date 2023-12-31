/*
 * Copyright 2023 Daniel R. Pedersen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     https://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.djpedersen.bitemporal.bitemporaldatabase;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * A generic class to encapsulate both the context and temporal structure.
 * 
 * @author Daniel R. Pedersen
 * 
 * @param <IDTYPE>     the type of the structure's identifier
 * @param <STATE_ENUM> the type of the structure's state enum
 * @param <EVENT_ENUM> the type of the structure's event enum
 * @param <STRUCT>     the type of the structure
 */
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TemporalSnapshot<IDTYPE, STATE_ENUM extends Enum<?>, EVENT_ENUM extends Enum<?>, STRUCT extends TemporalStructureInterface<IDTYPE, STATE_ENUM, EVENT_ENUM>> {

	/**
	 * The temporal context of the associated temporal structure
	 */
	public final TemporalContext context;

	/**
	 * The temporal structure
	 */
	public final STRUCT struct;

	/**
	 * The context handle referring to this snapshot
	 */
	public final ContextHandle<IDTYPE> contextHandle;

	/**
	 * Create a snapshot for the provided structure with a default Temporal Context (i.e. effective now, version 1, revision 0)
	 * 
	 * @param struct the structure to encapsulate
	 */
	public TemporalSnapshot(@NonNull final STRUCT struct) {
		this(new TemporalContext(), struct);
	}

	/**
	 * Create a snapshot for the provided structure and temporal context
	 * 
	 * @param context the context of the structure
	 * @param struct  the structure to encapsulate
	 */
	public TemporalSnapshot(@NonNull final TemporalContext context, @NonNull final STRUCT struct) {
		this.context = context;
		this.struct = struct;
		this.contextHandle = new ContextHandle<>(this.struct.getIdentifier(), this.context.version, this.context.revision);
	}
}
