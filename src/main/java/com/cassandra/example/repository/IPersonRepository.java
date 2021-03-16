package com.cassandra.example.repository;

import com.cassandra.example.domain.Person;
import org.springframework.data.cassandra.repository.CassandraRepository;

//@Repository
public interface IPersonRepository extends CassandraRepository<Person, String> {
}
