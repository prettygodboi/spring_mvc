package com.test.spring.mvc.dao;

import com.test.spring.mvc.models.Book;
import com.test.spring.mvc.models.Person;

//@Component
//public class PersonDao {
//    private final JdbcTemplate jdbcTemplate;
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public PersonDao(JdbcTemplate jdbcTemplate, SessionFactory sessionFactory) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Person> getPeople() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select p from  Person p", Person.class).getResultList();
//    }
//
//    @Transactional
//    public Optional<Person> getPersonByEmail(String email) {
//        return jdbcTemplate.query("SELECT * FROM people WHERE email=?", new BeanPropertyRowMapper<>(Person.class), new Object[]{email})
//                .stream().findAny();
//    }
//
//    @Transactional
//    public Person getPersonById(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Person.class, id);
//    }
//
//    @Transactional
//    public List<Book> getBooksByPersonId(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Person person = session.get(Person.class, id);
//        return person.getBooks();
//    }
//
//    @Transactional
//    public void createNewPerson(Person person) {
//        Session session = sessionFactory.getCurrentSession();
//        session.persist(person);
//    }
//
//    @Transactional
//    public void editPerson(int id, Person updatedPerson) {
//        Session session = sessionFactory.getCurrentSession();
//        Person person = session.get(Person.class, id);
//        person.setName(updatedPerson.getName());
//        person.setEmail(updatedPerson.getEmail());
//        person.setAge(updatedPerson.getAge());
//        session.persist(person);
//
////        jdbcTemplate.update("UPDATE people SET name=?, age=?, email=? WHERE id=?", updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
//    }
//
//    @Transactional
//    public void deletePerson(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Person person = session.get(Person.class, id);
//        session.remove(person);
////        jdbcTemplate.update("DELETE FROM people WHERE id=?", id);
//    }
//
//    public void testMultipleUpdate() {
//        List<Person> people = create100people();
//        long before = System.currentTimeMillis();
//        for (Person person : people) {
//            jdbcTemplate.update("INSERT INTO person(id,name, age, email) VALUES(?,?,?,?)", person.getId(), person.getName(), person.getAge(), person.getEmail());
//        }
//        long after = System.currentTimeMillis();
//        System.out.println(after - before);
//    }
//
//    public void testBatchUpdate() {
//        List<Person> people = create100people();
//        long before = System.currentTimeMillis();
//
//        jdbcTemplate.batchUpdate("INSERT INTO person(id, name, age, email) VALUES(?,?,?,?)", new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setInt(1, people.get(i).getId());
//                ps.setString(2, people.get(i).getName());
//                ps.setInt(3, people.get(i).getAge());
//                ps.setString(4, people.get(i).getEmail());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return people.size();
//            }
//        });
//
//        long after = System.currentTimeMillis();
//        System.out.println(after - before);
//    }
//
//    private static List<Person> create100people() {
//        List<Person> people = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            people.add(new Person("name" + i, 21, "sad" + 1 + "@asd.r"));
//        }
//        return people;
//    }
//}
