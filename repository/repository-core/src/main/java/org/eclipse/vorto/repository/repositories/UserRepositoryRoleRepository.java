/**
 * Copyright (c) 2020 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.vorto.repository.repositories;

import org.eclipse.vorto.repository.domain.UserRepositoryRoles;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Yields all user-repository role associations, which is read-only at runtime and limited to
 * expressing sysadmin users at this time.<br/>
 * The repository is intended to be read-only at runtime after initialization, so no cache eviction
 * strategy is used.
 */
@Repository
public interface UserRepositoryRoleRepository extends CrudRepository<UserRepositoryRoles, Long> {

    @Override
    @CacheEvict(value = "userRepositoryRolesCache", allEntries = true)
    <S extends UserRepositoryRoles> S save(S s);

    @Override
    @CacheEvict(value = "userRepositoryRolesCache", allEntries = true)
    <S extends UserRepositoryRoles> Iterable<S> save(Iterable<S> iterable);

    @Override
    @Cacheable("userRepositoryRolesCache")
    Iterable<UserRepositoryRoles> findAll();

    @Override
    @CacheEvict(value = "userRepositoryRolesCache", allEntries = true)
    void delete(Long aLong);

    @Override
    @CacheEvict(value = "userRepositoryRolesCache", allEntries = true)
    void delete(UserRepositoryRoles userRepositoryRoles);

    @Override
    @CacheEvict(value = "userRepositoryRolesCache", allEntries = true)
    void delete(Iterable<? extends UserRepositoryRoles> iterable);

    @Override
    @CacheEvict(value = "userRepositoryRolesCache", allEntries = true)
    void deleteAll();
}
