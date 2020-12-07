export const constant = {
    "baseUrl" : "http://localhost:8080/BookStore",
    "urls" : {
        "register" : "/user",
        "login": "/authenticate",
        "getUserDeatils": "/user", 
        "updateUserDetails": "/user",
        "changepwd": "/changePwd",       
        // "register" : "/api/registerUser",
        // "login": "/api/login",
        // "getUserDeatils": "api/userDetails",
        // "updateUserDetails": "api/updateUser",
        "addBook": "/api/addBook",
        "getAllBooksByemail": "/api/getAllBooksByEmail",
        "updateBookDetails": "/api/updateBookDetails",
        "deleteBook": "/api/delete",
        "getAllBooksToBuy": "/api/getBooksToBuy",
        "addItemToCart": "/api/addBookToCart",
        "getCartItems": "/api/getCartItems",
        "deleteItemFromCart": "/api/deleteItemFromCart",
        // "changepwd": "/api/changePassword",
        "uploadPhoto": "/api/uploadPhoto",
        "getImages": "/api/getPhotosByBookISBNAndEmail",
        "deleteImage": "/api/deleteImage",
        "logout": "/api/logout",
        "resetPassword": "/api/resetPassword"
    }
}