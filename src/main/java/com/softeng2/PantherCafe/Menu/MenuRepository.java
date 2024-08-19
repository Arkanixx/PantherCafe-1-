package com.softeng2.PantherCafe.Menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, String> {

    @Query("SELECT i FROM Menu i WHERE i.itemName= ?1")
    Optional<Menu> findMenuByItemName(String name);
}
