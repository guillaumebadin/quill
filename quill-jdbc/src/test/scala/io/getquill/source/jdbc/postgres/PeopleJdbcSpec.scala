package io.getquill.source.jdbc.postgres

import io.getquill._
import io.getquill.source.sql.PeopleSpec
import io.getquill.quotation.Quoted
import io.getquill.quotation.IsDynamic
import io.getquill.source.sql.mirror.mirrorSource

class PeopleJdbcSpec extends PeopleSpec {

  override def beforeAll = {
    val t = testPostgresDB.transaction {
      testPostgresDB.run(query[Couple].delete)
      testPostgresDB.run(query[Person].filter(_.age > 0).delete)
      testPostgresDB.run(peopleInsert).using(peopleEntries)
      testPostgresDB.run(couplesInsert).using(couplesEntries)
    }
  }

  "Example 1 - differences" in {
    testPostgresDB.run(`Ex 1 differences`) mustEqual `Ex 1 expected result`
  }

  "Example 2 - range simple" in {
    testPostgresDB.run(`Ex 2 rangeSimple`).using(`Ex 2 param 1`, `Ex 2 param 2`) mustEqual `Ex 2 expected result`
  }

  "Example 3 - satisfies" in {
    testPostgresDB.run(`Ex 3 satisfies`) mustEqual `Ex 3 expected result`
  }

  "Example 4 - satisfies" in {
    testPostgresDB.run(`Ex 4 satisfies`) mustEqual `Ex 4 expected result`
  }

  "Example 5 - compose" in {
    testPostgresDB.run(`Ex 5 compose`).using(`Ex 5 param 1`, `Ex 5 param 2`) mustEqual `Ex 5 expected result`
  }

  "Example 6 - predicate 0" in {
    testPostgresDB.run(satisfies(eval(`Ex 6 predicate`))) mustEqual `Ex 6 expected result`
  }

  "Example 7 - predicate 1" in {
    testPostgresDB.run(satisfies(eval(`Ex 7 predicate`))) mustEqual `Ex 7 expected result`
  }
}
