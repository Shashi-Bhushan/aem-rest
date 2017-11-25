package com.shabhushan.rest.service.impl

import com.shabhushan.rest.model.UserModel
import com.shabhushan.rest.service.UserService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Service

/**
 * @author Shashi Bhushan
 *
 * Change History:
 * Revision         Date            Dev             Comment
 * ----------------------------------------------------------------------------------------
 * 1.0              25/11/17         Shashi
 */
@Slf4j
@CompileStatic
@Component(label = "User Service", immediate = true, metatype = false, enabled = true)
@Service(UserService)
class UserServiceImpl implements UserService {

    private List<UserModel> userList = []

    @Override
    boolean createUser(int id, String name) {
        if(userExists(id)) {
            return false
        } else {
            userList.add(new UserModel(id: id, name: name))
        }
    }

    @Override
    boolean createUser() {
        return false
    }

    @Override
    String readUser(int id) {
        return null
    }

    @Override
    String readUser() {
        return null
    }

    @Override
    boolean updateUser(int id, String name) {
        return false
    }

    @Override
    boolean deleteUser(int id) {
        return false
    }

    @Override
    boolean deleteAllUsers() {
        return false
    }

    @Override
    boolean userExists(int id) {
        return userList.findAll { UserModel user ->
            user.id == id
        }.size() == 0 ? false : true
    }
}
