#import "enunciate-common.h"
#ifndef DEF_NOVAGLOBALAPIDL_SECURITYRole_H
#define DEF_NOVAGLOBALAPIDL_SECURITYRole_H

/**
 *  Account Roles.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
enum NOVAGLOBALAPIDL_SECURITYRole
{

  /**
   *  Basic Site User.

   */
  NOVAGLOBALAPI_DL_SECURITY_ROLE_USER,

  /**
   *  Site Admin.

   */
  NOVAGLOBALAPI_DL_SECURITY_ROLE_ADMIN
};
/**
 * Reads a Role from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The Role, or NULL if unable to be read.
 */
static enum NOVAGLOBALAPIDL_SECURITYRole *xmlTextReaderReadNOVAGLOBALAPIDL_SECURITYRoleType(xmlTextReaderPtr reader);

/**
 * Writes a Role to XML.
 *
 * @param writer The XML writer.
 * @param _role The Role to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNOVAGLOBALAPIDL_SECURITYRoleType(xmlTextWriterPtr writer, enum NOVAGLOBALAPIDL_SECURITYRole *_role);

/**
 * Utility method for getting the enum value for a string.
 *
 * @param _role The string to format.
 * @return The enum value or NULL on error.
 */
static enum NOVAGLOBALAPIDL_SECURITYRole *formatStringToNOVAGLOBALAPIDL_SECURITYRoleType(NSString *_role);

/**
 * Utility method for getting the string value of Role.
 *
 * @param _role The Role to format.
 * @return The string value or NULL on error.
 */
static NSString *formatNOVAGLOBALAPIDL_SECURITYRoleTypeToString(enum NOVAGLOBALAPIDL_SECURITYRole *_role);
#endif /* DEF_NOVAGLOBALAPIDL_SECURITYRole_H */
#ifndef DEF_NOVAGLOBALAPIDL_SECURITYPermission_H
#define DEF_NOVAGLOBALAPIDL_SECURITYPermission_H

/**
 *  Account Permissions.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
enum NOVAGLOBALAPIDL_SECURITYPermission
{

  /**
   *  Edit Self.

   */
  NOVAGLOBALAPI_DL_SECURITY_PERMISSION_EDIT_SELF,

  /**
   *  Delete Self.

   */
  NOVAGLOBALAPI_DL_SECURITY_PERMISSION_DELETE_SELF
};
/**
 * Reads a Permission from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The Permission, or NULL if unable to be read.
 */
static enum NOVAGLOBALAPIDL_SECURITYPermission *xmlTextReaderReadNOVAGLOBALAPIDL_SECURITYPermissionType(xmlTextReaderPtr reader);

/**
 * Writes a Permission to XML.
 *
 * @param writer The XML writer.
 * @param _permission The Permission to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNOVAGLOBALAPIDL_SECURITYPermissionType(xmlTextWriterPtr writer, enum NOVAGLOBALAPIDL_SECURITYPermission *_permission);

/**
 * Utility method for getting the enum value for a string.
 *
 * @param _permission The string to format.
 * @return The enum value or NULL on error.
 */
static enum NOVAGLOBALAPIDL_SECURITYPermission *formatStringToNOVAGLOBALAPIDL_SECURITYPermissionType(NSString *_permission);

/**
 * Utility method for getting the string value of Permission.
 *
 * @param _permission The Permission to format.
 * @return The string value or NULL on error.
 */
static NSString *formatNOVAGLOBALAPIDL_SECURITYPermissionTypeToString(enum NOVAGLOBALAPIDL_SECURITYPermission *_permission);
#endif /* DEF_NOVAGLOBALAPIDL_SECURITYPermission_H */

@class NOVAGLOBALAPIDL_SECURITYCredentials;
@class NOVAGLOBALAPIDL_SECURITYIdentity;
@class NOVAGLOBALAPIDIVELOGDiveLogEntity;
@class NOVAGLOBALAPIDIVELOGUserAccount;

#ifndef DEF_NOVAGLOBALAPIDL_SECURITYCredentials_H
#define DEF_NOVAGLOBALAPIDL_SECURITYCredentials_H

/**
 *  User Entered Credentials.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
@interface NOVAGLOBALAPIDL_SECURITYCredentials : NSObject <EnunciateXML>
{
  @private
    NSString *_username;
    NSString *_password;
}

/**
 * the username
 */
- (NSString *) username;

/**
 * the username
 */
- (void) setUsername: (NSString *) newUsername;

/**
 * the password
 */
- (NSString *) password;

/**
 * the password
 */
- (void) setPassword: (NSString *) newPassword;
@end /* interface NOVAGLOBALAPIDL_SECURITYCredentials */

#endif /* DEF_NOVAGLOBALAPIDL_SECURITYCredentials_H */
#ifndef DEF_NOVAGLOBALAPIDL_SECURITYIdentity_H
#define DEF_NOVAGLOBALAPIDL_SECURITYIdentity_H

/**
 *  User Identity implements Serializable.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
@interface NOVAGLOBALAPIDL_SECURITYIdentity : NSObject <EnunciateXML>
{
  @private
    int *_status;
    NSArray *_roles;
    NSArray *_permissions;
    BOOL _rememberMe;
    NSString *_publicApiKey;
    NSString *_privateApiKey;
    NSDate *_apiKeyExpiration;
}

/**
 * the status
 */
- (int *) status;

/**
 * the status
 */
- (void) setStatus: (int *) newStatus;

/**
 * the roles
 */
- (NSArray *) roles;

/**
 * the roles
 */
- (void) setRoles: (NSArray *) newRoles;

/**
 * the permissions
 */
- (NSArray *) permissions;

/**
 * the permissions
 */
- (void) setPermissions: (NSArray *) newPermissions;

/**
 * the rememberMe
 */
- (BOOL) rememberMe;

/**
 * the rememberMe
 */
- (void) setRememberMe: (BOOL) newRememberMe;

/**
 * the apiKey
 */
- (NSString *) publicApiKey;

/**
 * the apiKey
 */
- (void) setPublicApiKey: (NSString *) newPublicApiKey;

/**
 * the apiKey
 */
- (NSString *) privateApiKey;

/**
 * the apiKey
 */
- (void) setPrivateApiKey: (NSString *) newPrivateApiKey;

/**
 * the apiKeyExpiration
 */
- (NSDate *) apiKeyExpiration;

/**
 * the apiKeyExpiration
 */
- (void) setApiKeyExpiration: (NSDate *) newApiKeyExpiration;
@end /* interface NOVAGLOBALAPIDL_SECURITYIdentity */

#endif /* DEF_NOVAGLOBALAPIDL_SECURITYIdentity_H */
#ifndef DEF_NOVAGLOBALAPIDIVELOGDiveLogEntity_H
#define DEF_NOVAGLOBALAPIDIVELOGDiveLogEntity_H

/**
 *  Base Entity Element for all DiveLog Entities.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
@interface NOVAGLOBALAPIDIVELOGDiveLogEntity : NSObject
{
  @private
    int *_identifier;
    int *_version;
    NSDate *_created;
    NSDate *_updated;
}

/**
 * the id
 */
- (int *) identifier;

/**
 * the id
 */
- (void) setIdentifier: (int *) newIdentifier;

/**
 * the version
 */
- (int *) version;

/**
 * the version
 */
- (void) setVersion: (int *) newVersion;

/**
 * the created
 */
- (NSDate *) created;

/**
 * the created
 */
- (void) setCreated: (NSDate *) newCreated;

/**
 * the updated
 */
- (NSDate *) updated;

/**
 * the updated
 */
- (void) setUpdated: (NSDate *) newUpdated;
@end /* interface NOVAGLOBALAPIDIVELOGDiveLogEntity */

#endif /* DEF_NOVAGLOBALAPIDIVELOGDiveLogEntity_H */
#ifndef DEF_NOVAGLOBALAPIDIVELOGUserAccount_H
#define DEF_NOVAGLOBALAPIDIVELOGUserAccount_H

/**
 *  User Model.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
@interface NOVAGLOBALAPIDIVELOGUserAccount : NOVAGLOBALAPIDIVELOGDiveLogEntity <EnunciateXML>
{
  @private
    NSString *_firstName;
    NSString *_lastName;
    NSString *_country;
    NSString *_state;
    NSString *_city;
    NSString *_email;
    NSString *_password;
    NSArray *_roles;
    NSArray *_permissions;
    NSString *_publicApiKey;
    NSString *_privateApiKey;
    NSDate *_apiKeyExpiration;
}

/**
 * the firstName
 */
- (NSString *) firstName;

/**
 * the firstName
 */
- (void) setFirstName: (NSString *) newFirstName;

/**
 * the lastName
 */
- (NSString *) lastName;

/**
 * the lastName
 */
- (void) setLastName: (NSString *) newLastName;

/**
 * the country
 */
- (NSString *) country;

/**
 * the country
 */
- (void) setCountry: (NSString *) newCountry;

/**
 * the state
 */
- (NSString *) state;

/**
 * the state
 */
- (void) setState: (NSString *) newState;

/**
 * the city
 */
- (NSString *) city;

/**
 * the city
 */
- (void) setCity: (NSString *) newCity;

/**
 * the email
 */
- (NSString *) email;

/**
 * the email
 */
- (void) setEmail: (NSString *) newEmail;

/**
 * the password
 */
- (NSString *) password;

/**
 * the password
 */
- (void) setPassword: (NSString *) newPassword;

/**
 * the roles
 */
- (NSArray *) roles;

/**
 * the roles
 */
- (void) setRoles: (NSArray *) newRoles;

/**
 * the permissions
 */
- (NSArray *) permissions;

/**
 * the permissions
 */
- (void) setPermissions: (NSArray *) newPermissions;

/**
 * the apiKey
 */
- (NSString *) publicApiKey;

/**
 * the apiKey
 */
- (void) setPublicApiKey: (NSString *) newPublicApiKey;

/**
 * the apiKey
 */
- (NSString *) privateApiKey;

/**
 * the apiKey
 */
- (void) setPrivateApiKey: (NSString *) newPrivateApiKey;

/**
 * the apiKeyExpiration
 */
- (NSDate *) apiKeyExpiration;

/**
 * the apiKeyExpiration
 */
- (void) setApiKeyExpiration: (NSDate *) newApiKeyExpiration;
@end /* interface NOVAGLOBALAPIDIVELOGUserAccount */

#endif /* DEF_NOVAGLOBALAPIDIVELOGUserAccount_H */
