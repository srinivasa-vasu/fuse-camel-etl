/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fuse.ra.camel.resource;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fuse.ra.camel.jpa.source.MySQLDS;
import com.fuse.ra.camel.jpa.source.PSQLDS;

/**
 * Persistence resources
 * 
 * @author Srinivasa Vasu
 * @date 08-Aug-2016
 */
public class Resources {
    @Produces
    @PersistenceContext(unitName = "psqljws")
    @PSQLDS
    private EntityManager sourceManager;

    @Produces
    @PersistenceContext(unitName = "mysqljws")
    @MySQLDS
    private EntityManager targetManager;

}
