package org.example.projectspringojt.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.example.projectspringojt.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query("SELECT u FROM User u")
  List<User> findUsersWithPagination(Pageable pageable);

  // Search users by keyword with pagination (for search functionality)
  @Query("SELECT u FROM User u WHERE u.userName LIKE %:keyword% OR u.email LIKE %:keyword%")
  List<User> searchUsersWithPagination(String keyword, Pageable pageable);

  // Count all users (for calculating total pages)
  @Query("SELECT COUNT(u) FROM User u")
  int countAllUsers();

  // Count users by search keyword (for calculating total pages in search results)
  @Query("SELECT COUNT(u) FROM User u WHERE u.userName LIKE %:keyword% OR u.email LIKE %:keyword%")
  int countUsersByKeyword(String keyword);

  //List<User> findByNameContainingOrEmailContaining(String userName, String email);

  @Query("SELECT u FROM User u " +
      "WHERE u.userName LIKE %:searchText% " +
      "OR u.address LIKE %:searchText% " +
      "OR u.email LIKE %:searchText% " +
      "OR u.phone LIKE %:searchText% " +
      "OR u.Dob LIKE %:searchText%  ")
  List<User> findByAllFields( String searchText);

  Optional<User> findByUserName(String userName);
}
