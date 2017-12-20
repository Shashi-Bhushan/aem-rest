package com.shabhushan.rest.service.impl

import com.shabhushan.rest.constants.BasicConstants
import com.shabhushan.rest.model.UserModel
import com.shabhushan.rest.service.UserService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Service
import org.apache.sling.commons.json.JSONArray
import org.apache.sling.commons.json.JSONObject

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

    final static String[] nameList = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split('') as String[]

    final static Random random = new Random()

    final static int max = 10000

    private List<UserModel> userList = []

    @Override
    boolean createUser(int id, String name) {
        if (userExists(id)) {
            return false
        } else {
            userList.add(new UserModel(id: id, name: name))
        }
    }

    @Override
    boolean createUser(int id) {
        return this.createUser(id, "")
    }

    @Override
    String readUser(int id) {
        JSONObject object = new JSONObject()

        if (userExists(id)) {
            UserModel user = userList.findAll {
                it.id == id
            }.get(0)

            object.put("id", user.id)
            object.put("name", user.name)

            return object.toString()
        } else {
            object.put(BasicConstants.USER_ERROR_KEY, "user does not exist")
            return object.toString()
        }
    }

    @Override
    String readAllUsers() {
        JSONArray users = new JSONArray()
        userList.each {
            JSONObject user = new JSONObject()

            user.put("id", it.id)
            user.put("name", it.name)

            users.put(user)
        }
        return users.toString()
    }

    @Override
    boolean updateUser(int id, String name) {
        userList.findAll {
            it.id == id
        }.each {
            it.name = name
        }

        return true
    }

    @Override
    boolean deleteUser(int id) {
        return userList.removeAll(
            userList.findAll {
                it.id == id
            }
        )
    }

    @Override
    boolean deleteAllUsers() {
        userList = []
        return true
    }

    @Override
    boolean createRandomUsers(int number) {
        userList = []

        (1..number).each{
            // get a random number
            int id = random.nextInt(max + 1)

            userList << new UserModel(id: it, name: nameList[id % 26])
        }

        return true
    }

    @Override
    boolean userExists(int id) {
        return userList.findAll { UserModel user ->
            user.id == id
        }.size() == 0 ? false : true
    }
}
