package com.userscrud.users_crud.handler.exceptions


enum class ApiError(val errorCode: String, val message: String, val httpStatusCode: HttpStatusCode) {

  //Users Exception

  //Authorization
  FORBIDDEN("UE01", "Cannot access other users information", HttpStatusCode.FORBIDDEN/*Forbidden*/),
  //Request
  BAD_BODY("UR01", "The request body have missing fields, bad values or is malformed", HttpStatusCode.BAD_REQUEST/*Bad request*/),
  //Persistence
  USER_ALREADY_EXISTS("UP03","The user already exists", HttpStatusCode.INTERNAL_SERVER_ERROR),
  USER_DOES_NOT_EXISTS("UP04", "The user doesn't exists", HttpStatusCode.BAD_REQUEST/*Bad request*/),

  //Mongo Error
  MONGO_EXCEPTION("ME01","Error during the operation",HttpStatusCode.INTERNAL_SERVER_ERROR/*Server internal error*/)
}
