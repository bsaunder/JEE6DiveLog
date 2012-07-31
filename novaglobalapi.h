#import "enunciate-common.h"
#ifndef DEF_NOVAGLOBALAPINS0Role_H
#define DEF_NOVAGLOBALAPINS0Role_H

/**
 *  Account Roles.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
enum NOVAGLOBALAPINS0Role
{

  /**
   *  Basic Site User.

   */
  NOVAGLOBALAPI_NS0_ROLE_USER,

  /**
   *  Site Admin.

   */
  NOVAGLOBALAPI_NS0_ROLE_ADMIN
};
/**
 * Reads a Role from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The Role, or NULL if unable to be read.
 */
static enum NOVAGLOBALAPINS0Role *xmlTextReaderReadNOVAGLOBALAPINS0RoleType(xmlTextReaderPtr reader);

/**
 * Writes a Role to XML.
 *
 * @param writer The XML writer.
 * @param _role The Role to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNOVAGLOBALAPINS0RoleType(xmlTextWriterPtr writer, enum NOVAGLOBALAPINS0Role *_role);

/**
 * Utility method for getting the enum value for a string.
 *
 * @param _role The string to format.
 * @return The enum value or NULL on error.
 */
static enum NOVAGLOBALAPINS0Role *formatStringToNOVAGLOBALAPINS0RoleType(NSString *_role);

/**
 * Utility method for getting the string value of Role.
 *
 * @param _role The Role to format.
 * @return The string value or NULL on error.
 */
static NSString *formatNOVAGLOBALAPINS0RoleTypeToString(enum NOVAGLOBALAPINS0Role *_role);
#endif /* DEF_NOVAGLOBALAPINS0Role_H */
#ifndef DEF_NOVAGLOBALAPINS0Permission_H
#define DEF_NOVAGLOBALAPINS0Permission_H

/**
 *  Account Permissions.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
enum NOVAGLOBALAPINS0Permission
{

  /**
   *  Edit Self.

   */
  NOVAGLOBALAPI_NS0_PERMISSION_EDIT_SELF,

  /**
   *  Delete Self.

   */
  NOVAGLOBALAPI_NS0_PERMISSION_DELETE_SELF
};
/**
 * Reads a Permission from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The Permission, or NULL if unable to be read.
 */
static enum NOVAGLOBALAPINS0Permission *xmlTextReaderReadNOVAGLOBALAPINS0PermissionType(xmlTextReaderPtr reader);

/**
 * Writes a Permission to XML.
 *
 * @param writer The XML writer.
 * @param _permission The Permission to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNOVAGLOBALAPINS0PermissionType(xmlTextWriterPtr writer, enum NOVAGLOBALAPINS0Permission *_permission);

/**
 * Utility method for getting the enum value for a string.
 *
 * @param _permission The string to format.
 * @return The enum value or NULL on error.
 */
static enum NOVAGLOBALAPINS0Permission *formatStringToNOVAGLOBALAPINS0PermissionType(NSString *_permission);

/**
 * Utility method for getting the string value of Permission.
 *
 * @param _permission The Permission to format.
 * @return The string value or NULL on error.
 */
static NSString *formatNOVAGLOBALAPINS0PermissionTypeToString(enum NOVAGLOBALAPINS0Permission *_permission);
#endif /* DEF_NOVAGLOBALAPINS0Permission_H */

@class NOVAGLOBALAPINS0DiveLogEntity;
@class NOVAGLOBALAPINS0UserAccount;

#ifndef DEF_NOVAGLOBALAPINS0DiveLogEntity_H
#define DEF_NOVAGLOBALAPINS0DiveLogEntity_H

/**
 *  Base Entity Element for all DiveLog Entities.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
@interface NOVAGLOBALAPINS0DiveLogEntity : NSObject
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
@end /* interface NOVAGLOBALAPINS0DiveLogEntity */

#endif /* DEF_NOVAGLOBALAPINS0DiveLogEntity_H */
#ifndef DEF_NOVAGLOBALAPINS0UserAccount_H
#define DEF_NOVAGLOBALAPINS0UserAccount_H

/**
 *  User Model.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
@interface NOVAGLOBALAPINS0UserAccount : NOVAGLOBALAPINS0DiveLogEntity <EnunciateXML>
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
@end /* interface NOVAGLOBALAPINS0UserAccount */

#endif /* DEF_NOVAGLOBALAPINS0UserAccount_H */
