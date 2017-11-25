package com.shabhushan.rest.service

/**
 * @author Shashi Bhushan
 *
 * Change History:
 * Revision         Date            Dev             Comment
 * ----------------------------------------------------------------------------------------
 * 1.0              25/11/17         Shashi
 */
interface UserService {
    boolean createUser(int id, String name)
    boolean createUser()

    String readUser(int id)
    String readUser()

    boolean updateUser(int id, String name)

    boolean deleteUser(int id)
    boolean deleteAllUsers()

    boolean userExists(int id)
}
