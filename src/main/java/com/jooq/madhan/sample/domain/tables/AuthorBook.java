/*
 * This file is generated by jOOQ.
*/
package com.jooq.madhan.sample.domain.tables;


import com.jooq.madhan.sample.domain.Jooqschema;
import com.jooq.madhan.sample.domain.Keys;
import com.jooq.madhan.sample.domain.tables.records.AuthorBookRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AuthorBook extends TableImpl<AuthorBookRecord> {

    private static final long serialVersionUID = -412065925;

    /**
     * The reference instance of <code>jooqschema.author_book</code>
     */
    public static final AuthorBook AUTHOR_BOOK = new AuthorBook();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AuthorBookRecord> getRecordType() {
        return AuthorBookRecord.class;
    }

    /**
     * The column <code>jooqschema.author_book.author_id</code>.
     */
    public final TableField<AuthorBookRecord, Integer> AUTHOR_ID = createField("author_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>jooqschema.author_book.book_id</code>.
     */
    public final TableField<AuthorBookRecord, Integer> BOOK_ID = createField("book_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>jooqschema.author_book</code> table reference
     */
    public AuthorBook() {
        this("author_book", null);
    }

    /**
     * Create an aliased <code>jooqschema.author_book</code> table reference
     */
    public AuthorBook(String alias) {
        this(alias, AUTHOR_BOOK);
    }

    private AuthorBook(String alias, Table<AuthorBookRecord> aliased) {
        this(alias, aliased, null);
    }

    private AuthorBook(String alias, Table<AuthorBookRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Jooqschema.JOOQSCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AuthorBookRecord> getPrimaryKey() {
        return Keys.KEY_AUTHOR_BOOK_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AuthorBookRecord>> getKeys() {
        return Arrays.<UniqueKey<AuthorBookRecord>>asList(Keys.KEY_AUTHOR_BOOK_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<AuthorBookRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AuthorBookRecord, ?>>asList(Keys.FK_AB_AUTHOR);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorBook as(String alias) {
        return new AuthorBook(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AuthorBook rename(String name) {
        return new AuthorBook(name, null);
    }
}