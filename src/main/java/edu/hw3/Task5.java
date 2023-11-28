package edu.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;

public final class Task5 {
    private Task5() { }

    public static Contact[] parseContacts(String[] names, Order order) {
        if (names == null || names.length == 0) {
            return new Contact[0];
        }
        var contacts = new ArrayList<Contact>();
        for (var name : names) {
            var fullName = name.split(" ");
            contacts.add(new Contact(fullName[0], fullName.length == 2 ? fullName[1] : null));
        }
        Contact[] result = new Contact[names.length];
        contacts.toArray(result);
        if (order == Order.ASK) {
            Arrays.sort(result);
        } else {
            Arrays.sort(result, Collections.reverseOrder());
        }
        return result;
    }

    public record Contact(@NotNull String name, String surname) implements Comparable<Contact> {
        @Override
        public int compareTo(@NotNull Contact contact) {
            if (this.surname != null && contact.surname() != null) {
                return this.surname.compareTo(contact.surname());
            } else if (this.surname != null) {
                return this.surname.compareTo(contact.name());
            } else if (contact.surname != null) {
                return this.name.compareTo(contact.surname());
            } else {
                return this.name.compareTo(contact.name());
            }
        }
    }

    public enum Order {
        ASK,
        DESK
    }
}
