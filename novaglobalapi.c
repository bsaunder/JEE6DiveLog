#include <enunciate-common.c>
#ifndef DEF_novaglobalapi_divelog_diveLogEntity_H
#define DEF_novaglobalapi_divelog_diveLogEntity_H

/**
 *  Base Entity Element for all DiveLog Entities.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
struct novaglobalapi_divelog_diveLogEntity {


  /**
   * the id
   */
  int *id;

  /**
   * the version
   */
  int *version;

  /**
   * the created
   */
  struct tm *created;

  /**
   * the updated
   */
  struct tm *updated;
};

/**
 * Reads a DiveLogEntity from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The DiveLogEntity, or NULL in case of error.
 */
static struct novaglobalapi_divelog_diveLogEntity *xmlTextReaderReadDivelogDiveLogEntityType(xmlTextReaderPtr reader);

/**
 * Writes a DiveLogEntity to XML.
 *
 * @param writer The XML writer.
 * @param _diveLogEntity The DiveLogEntity to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteDivelogDiveLogEntityType(xmlTextWriterPtr writer, struct novaglobalapi_divelog_diveLogEntity *_diveLogEntity);

/**
 * Frees the elements of a DiveLogEntity.
 *
 * @param _diveLogEntity The DiveLogEntity to free.
 */
static void freeDivelogDiveLogEntityType(struct novaglobalapi_divelog_diveLogEntity *_diveLogEntity);

#endif /* DEF_novaglobalapi_divelog_diveLogEntity_H */
#ifndef DEF_novaglobalapi_divelog_userAccount_H
#define DEF_novaglobalapi_divelog_userAccount_H

/**
 *  User Model.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
struct novaglobalapi_divelog_userAccount {


  /**
   * the id
   */
  int *id;

  /**
   * the version
   */
  int *version;

  /**
   * the created
   */
  struct tm *created;

  /**
   * the updated
   */
  struct tm *updated;

  /**
   * the firstName
   */
  xmlChar *firstName;

  /**
   * the lastName
   */
  xmlChar *lastName;

  /**
   * the country
   */
  xmlChar *country;

  /**
   * the state
   */
  xmlChar *state;

  /**
   * the city
   */
  xmlChar *city;

  /**
   * the email
   */
  xmlChar *email;

  /**
   * the password
   */
  xmlChar *password;

  /**
   * the roles
   */
  enum novaglobalapi_dl_security_role *roles;

  /**
   * Size of the roles array.
   */
  int _sizeof_roles;

  /**
   * the permissions
   */
  enum novaglobalapi_dl_security_permission *permissions;

  /**
   * Size of the permissions array.
   */
  int _sizeof_permissions;

  /**
   * the apiKey
   */
  xmlChar *apiKey;

  /**
   * the apiKeyExpiration
   */
  struct tm *apiKeyExpiration;
};

/**
 * Reads a UserAccount element from XML. The element to be read is "{http://www.bryansaunders.net/divelog}userAccount", and
 * it is assumed that the reader is pointing to the XML document (not the element).
 *
 * @param reader The XML reader.
 * @return The UserAccount, or NULL in case of error.
 */
struct novaglobalapi_divelog_userAccount *xml_read_novaglobalapi_divelog_userAccount(xmlTextReaderPtr reader);

/**
 * Writes a UserAccount to XML under element name "{http://www.bryansaunders.net/divelog}userAccount".
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
int xml_write_novaglobalapi_divelog_userAccount(xmlTextWriterPtr writer, struct novaglobalapi_divelog_userAccount *_userAccount);

/**
 * Frees a UserAccount.
 *
 * @param _userAccount The UserAccount to free.
 */
void free_novaglobalapi_divelog_userAccount(struct novaglobalapi_divelog_userAccount *_userAccount);

/**
 * Reads a UserAccount element from XML. The element to be read is "{http://www.bryansaunders.net/divelog}userAccount", and
 * it is assumed that the reader is already pointing to the element.
 *
 * @param reader The XML reader.
 * @return The UserAccount, or NULL in case of error.
 */
struct novaglobalapi_divelog_userAccount *xmlTextReaderReadDivelogUserAccountElement(xmlTextReaderPtr reader);

/**
 * Writes a UserAccount to XML under element name "{http://www.bryansaunders.net/divelog}userAccount".
 * Does NOT write the namespace prefixes.
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteDivelogUserAccountElement(xmlTextWriterPtr writer, struct novaglobalapi_divelog_userAccount *_userAccount);

/**
 * Writes a UserAccount to XML under element name "{http://www.bryansaunders.net/divelog}userAccount".
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @param writeNamespaces Whether to write the namespace prefixes.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteDivelogUserAccountElementNS(xmlTextWriterPtr writer, struct novaglobalapi_divelog_userAccount *_userAccount, int writeNamespaces);

/**
 * Frees the children of a UserAccount.
 *
 * @param _userAccount The UserAccount whose children are to be free.
 */
static void freeDivelogUserAccountElement(struct novaglobalapi_divelog_userAccount *_userAccount);

/**
 * Reads a UserAccount from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The UserAccount, or NULL in case of error.
 */
static struct novaglobalapi_divelog_userAccount *xmlTextReaderReadDivelogUserAccountType(xmlTextReaderPtr reader);

/**
 * Writes a UserAccount to XML.
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteDivelogUserAccountType(xmlTextWriterPtr writer, struct novaglobalapi_divelog_userAccount *_userAccount);

/**
 * Frees the elements of a UserAccount.
 *
 * @param _userAccount The UserAccount to free.
 */
static void freeDivelogUserAccountType(struct novaglobalapi_divelog_userAccount *_userAccount);

#endif /* DEF_novaglobalapi_divelog_userAccount_H */
#ifndef DEF_novaglobalapi_dl_security_credentials_H
#define DEF_novaglobalapi_dl_security_credentials_H

/**
 *  User Entered Credentials.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
struct novaglobalapi_dl_security_credentials {


  /**
   * the username
   */
  xmlChar *username;

  /**
   * the password
   */
  xmlChar *password;
};

/**
 * Reads a Credentials element from XML. The element to be read is "{http://www.bryansaunders.net/divelog/security}credentials", and
 * it is assumed that the reader is pointing to the XML document (not the element).
 *
 * @param reader The XML reader.
 * @return The Credentials, or NULL in case of error.
 */
struct novaglobalapi_dl_security_credentials *xml_read_novaglobalapi_dl_security_credentials(xmlTextReaderPtr reader);

/**
 * Writes a Credentials to XML under element name "{http://www.bryansaunders.net/divelog/security}credentials".
 *
 * @param writer The XML writer.
 * @param _credentials The Credentials to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
int xml_write_novaglobalapi_dl_security_credentials(xmlTextWriterPtr writer, struct novaglobalapi_dl_security_credentials *_credentials);

/**
 * Frees a Credentials.
 *
 * @param _credentials The Credentials to free.
 */
void free_novaglobalapi_dl_security_credentials(struct novaglobalapi_dl_security_credentials *_credentials);

/**
 * Reads a Credentials element from XML. The element to be read is "{http://www.bryansaunders.net/divelog/security}credentials", and
 * it is assumed that the reader is already pointing to the element.
 *
 * @param reader The XML reader.
 * @return The Credentials, or NULL in case of error.
 */
struct novaglobalapi_dl_security_credentials *xmlTextReaderReadDl_securityCredentialsElement(xmlTextReaderPtr reader);

/**
 * Writes a Credentials to XML under element name "{http://www.bryansaunders.net/divelog/security}credentials".
 * Does NOT write the namespace prefixes.
 *
 * @param writer The XML writer.
 * @param _credentials The Credentials to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteDl_securityCredentialsElement(xmlTextWriterPtr writer, struct novaglobalapi_dl_security_credentials *_credentials);

/**
 * Writes a Credentials to XML under element name "{http://www.bryansaunders.net/divelog/security}credentials".
 *
 * @param writer The XML writer.
 * @param _credentials The Credentials to write.
 * @param writeNamespaces Whether to write the namespace prefixes.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteDl_securityCredentialsElementNS(xmlTextWriterPtr writer, struct novaglobalapi_dl_security_credentials *_credentials, int writeNamespaces);

/**
 * Frees the children of a Credentials.
 *
 * @param _credentials The Credentials whose children are to be free.
 */
static void freeDl_securityCredentialsElement(struct novaglobalapi_dl_security_credentials *_credentials);

/**
 * Reads a Credentials from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The Credentials, or NULL in case of error.
 */
static struct novaglobalapi_dl_security_credentials *xmlTextReaderReadDl_securityCredentialsType(xmlTextReaderPtr reader);

/**
 * Writes a Credentials to XML.
 *
 * @param writer The XML writer.
 * @param _credentials The Credentials to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteDl_securityCredentialsType(xmlTextWriterPtr writer, struct novaglobalapi_dl_security_credentials *_credentials);

/**
 * Frees the elements of a Credentials.
 *
 * @param _credentials The Credentials to free.
 */
static void freeDl_securityCredentialsType(struct novaglobalapi_dl_security_credentials *_credentials);

#endif /* DEF_novaglobalapi_dl_security_credentials_H */
#ifndef DEF_novaglobalapi_dl_security_permission_H
#define DEF_novaglobalapi_dl_security_permission_H

/**
 *  Account Permissions.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
enum novaglobalapi_dl_security_permission {

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
static enum novaglobalapi_dl_security_permission *xmlTextReaderReadDl_securityPermissionType(xmlTextReaderPtr reader);

/**
 * Writes a Permission to XML.
 *
 * @param writer The XML writer.
 * @param _permission The Permission to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteDl_securityPermissionType(xmlTextWriterPtr writer, enum novaglobalapi_dl_security_permission *_permission);

/**
 * Frees a Permission.
 *
 * @param _permission The Permission to free.
 */
static void freeDl_securityPermissionType(enum novaglobalapi_dl_security_permission *_permission);

#endif
#ifndef DEF_novaglobalapi_dl_security_role_H
#define DEF_novaglobalapi_dl_security_role_H

/**
 *  Account Roles.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
enum novaglobalapi_dl_security_role {

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
static enum novaglobalapi_dl_security_role *xmlTextReaderReadDl_securityRoleType(xmlTextReaderPtr reader);

/**
 * Writes a Role to XML.
 *
 * @param writer The XML writer.
 * @param _role The Role to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteDl_securityRoleType(xmlTextWriterPtr writer, enum novaglobalapi_dl_security_role *_role);

/**
 * Frees a Role.
 *
 * @param _role The Role to free.
 */
static void freeDl_securityRoleType(enum novaglobalapi_dl_security_role *_role);

#endif
#ifndef DEF_novaglobalapi_divelog_diveLogEntity_M
#define DEF_novaglobalapi_divelog_diveLogEntity_M

/**
 * Reads a DiveLogEntity from XML. The reader is assumed to be at the start element.
 *
 * @return the DiveLogEntity, or NULL in case of error.
 */
static struct novaglobalapi_divelog_diveLogEntity *xmlTextReaderReadDivelogDiveLogEntityType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct novaglobalapi_divelog_diveLogEntity *_diveLogEntity = calloc(1, sizeof(struct novaglobalapi_divelog_diveLogEntity));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeDivelogDiveLogEntityType(_diveLogEntity);
        free(_diveLogEntity);
        return NULL;
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "id", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}id of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
        _child_accessor = xmlTextReaderReadXsIntType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}id of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogDiveLogEntityType(_diveLogEntity);
          free(_diveLogEntity);
          return NULL;
        }

        _diveLogEntity->id = ((int*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "version", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}version of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
        _child_accessor = xmlTextReaderReadXsIntType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}version of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogDiveLogEntityType(_diveLogEntity);
          free(_diveLogEntity);
          return NULL;
        }

        _diveLogEntity->version = ((int*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "created", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}created of type {http://www.w3.org/2001/XMLSchema}dateTime.\n");
#endif
        _child_accessor = xmlTextReaderReadXsDateTimeType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}created of type {http://www.w3.org/2001/XMLSchema}dateTime.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogDiveLogEntityType(_diveLogEntity);
          free(_diveLogEntity);
          return NULL;
        }

        _diveLogEntity->created = ((struct tm*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "updated", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}updated of type {http://www.w3.org/2001/XMLSchema}dateTime.\n");
#endif
        _child_accessor = xmlTextReaderReadXsDateTimeType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}updated of type {http://www.w3.org/2001/XMLSchema}dateTime.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogDiveLogEntityType(_diveLogEntity);
          free(_diveLogEntity);
          return NULL;
        }

        _diveLogEntity->updated = ((struct tm*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {http://www.bryansaunders.net/divelog}diveLogEntity.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {http://www.bryansaunders.net/divelog}diveLogEntity. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
        }
#endif
        status = xmlTextReaderSkipElement(reader);
      }
    }
  }

  return _diveLogEntity;
}

/**
 * Writes a DiveLogEntity to XML.
 *
 * @param writer The XML writer.
 * @param _diveLogEntity The DiveLogEntity to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteDivelogDiveLogEntityType(xmlTextWriterPtr writer, struct novaglobalapi_divelog_diveLogEntity *_diveLogEntity) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;
  if (_diveLogEntity->id != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "id", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}id. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}int for element {}id...\n");
#endif
    status = xmlTextWriterWriteXsIntType(writer, (_diveLogEntity->id));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}int for element {}id. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}id. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_diveLogEntity->version != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "version", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}version. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}int for element {}version...\n");
#endif
    status = xmlTextWriterWriteXsIntType(writer, (_diveLogEntity->version));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}int for element {}version. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}version. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_diveLogEntity->created != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "created", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}created. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}dateTime for element {}created...\n");
#endif
    status = xmlTextWriterWriteXsDateTimeType(writer, (_diveLogEntity->created));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}dateTime for element {}created. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}created. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_diveLogEntity->updated != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "updated", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}updated. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}dateTime for element {}updated...\n");
#endif
    status = xmlTextWriterWriteXsDateTimeType(writer, (_diveLogEntity->updated));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}dateTime for element {}updated. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}updated. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

  return totalBytes;
}

/**
 * Frees the elements of a DiveLogEntity.
 *
 * @param _diveLogEntity The DiveLogEntity to free.
 */
static void freeDivelogDiveLogEntityType(struct novaglobalapi_divelog_diveLogEntity *_diveLogEntity) {
  int i;
  if (_diveLogEntity->id != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor id of type novaglobalapi_divelog_diveLogEntity...\n");
#endif
    freeXsIntType(_diveLogEntity->id);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor id of type novaglobalapi_divelog_diveLogEntity...\n");
#endif
    free(_diveLogEntity->id);
  }
  if (_diveLogEntity->version != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor version of type novaglobalapi_divelog_diveLogEntity...\n");
#endif
    freeXsIntType(_diveLogEntity->version);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor version of type novaglobalapi_divelog_diveLogEntity...\n");
#endif
    free(_diveLogEntity->version);
  }
  if (_diveLogEntity->created != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor created of type novaglobalapi_divelog_diveLogEntity...\n");
#endif
    freeXsDateTimeType(_diveLogEntity->created);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor created of type novaglobalapi_divelog_diveLogEntity...\n");
#endif
    free(_diveLogEntity->created);
  }
  if (_diveLogEntity->updated != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor updated of type novaglobalapi_divelog_diveLogEntity...\n");
#endif
    freeXsDateTimeType(_diveLogEntity->updated);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor updated of type novaglobalapi_divelog_diveLogEntity...\n");
#endif
    free(_diveLogEntity->updated);
  }
}
#endif /* DEF_novaglobalapi_divelog_diveLogEntity_M */
#ifndef DEF_novaglobalapi_divelog_userAccount_M
#define DEF_novaglobalapi_divelog_userAccount_M

/**
 * Reads a UserAccount element from XML. The element to be read is "{http://www.bryansaunders.net/divelog}userAccount", and
 * it is assumed that the reader is pointing to the XML document (not the element).
 *
 * @param reader The XML reader.
 * @return The UserAccount, or NULL in case of error.
 */
struct novaglobalapi_divelog_userAccount *xml_read_novaglobalapi_divelog_userAccount(xmlTextReaderPtr reader) {
  int status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
  return xmlTextReaderReadDivelogUserAccountElement(reader);
}

/**
 * Writes a UserAccount to XML under element name "{http://www.bryansaunders.net/divelog}userAccount".
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @return 1 if successful, 0 otherwise.
 */
int xml_write_novaglobalapi_divelog_userAccount(xmlTextWriterPtr writer, struct novaglobalapi_divelog_userAccount *_userAccount) {
  return xmlTextWriterWriteDivelogUserAccountElementNS(writer, _userAccount, 1);
}

/**
 * Frees a UserAccount.
 *
 * @param _userAccount The UserAccount to free.
 */
void free_novaglobalapi_divelog_userAccount(struct novaglobalapi_divelog_userAccount *_userAccount) {
  freeDivelogUserAccountType(_userAccount);
  free(_userAccount);
}

/**
 * Reads a UserAccount element from XML. The element to be read is "{http://www.bryansaunders.net/divelog}userAccount", and
 * it is assumed that the reader is pointing to that element.
 *
 * @param reader The XML reader.
 * @return The UserAccount, or NULL in case of error.
 */
struct novaglobalapi_divelog_userAccount *xmlTextReaderReadDivelogUserAccountElement(xmlTextReaderPtr reader) {
  struct novaglobalapi_divelog_userAccount *_userAccount = NULL;

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "userAccount", xmlTextReaderConstLocalName(reader)) == 0
    && xmlStrcmp(BAD_CAST "http://www.bryansaunders.net/divelog", xmlTextReaderConstNamespaceUri(reader)) == 0) {
#if DEBUG_ENUNCIATE > 1
    printf("Attempting to read root element {http://www.bryansaunders.net/divelog}userAccount.\n");
#endif
    _userAccount = xmlTextReaderReadDivelogUserAccountType(reader);
  }
#if DEBUG_ENUNCIATE
  if (_userAccount == NULL) {
    if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
      printf("attempt to read {http://www.bryansaunders.net/divelog}userAccount failed. current element: {}%s\n",  xmlTextReaderConstLocalName(reader));
    }
    else {
      printf("attempt to read {http://www.bryansaunders.net/divelog}userAccount failed. current element: {%s}%s\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
    }
  }
#endif

  return _userAccount;
}

/**
 * Writes a UserAccount to XML under element name "{http://www.bryansaunders.net/divelog}userAccount".
 * Does NOT write the namespace prefixes.
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @return 1 if successful, 0 otherwise.
 */
static int xmlTextWriterWriteDivelogUserAccountElement(xmlTextWriterPtr writer, struct novaglobalapi_divelog_userAccount *_userAccount) {
  return xmlTextWriterWriteDivelogUserAccountElementNS(writer, _userAccount, 0);
}

/**
 * Writes a UserAccount to XML under element name "{http://www.bryansaunders.net/divelog}userAccount".
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @param writeNamespaces Whether to write the namespace prefixes.
 * @return 1 if successful, 0 otherwise.
 */
static int xmlTextWriterWriteDivelogUserAccountElementNS(xmlTextWriterPtr writer, struct novaglobalapi_divelog_userAccount *_userAccount, int writeNamespaces) {
  int totalBytes = 0;
  int status;

  status = xmlTextWriterStartElementNS(writer, BAD_CAST "divelog", BAD_CAST "userAccount", NULL);
  if (status < 0) {
#if DEBUG_ENUNCIATE
    printf("unable to write start element {http://www.bryansaunders.net/divelog}userAccount. status: %i\n", status);
#endif
    return status;
  }
  totalBytes += status;

  if (writeNamespaces) {
#if DEBUG_ENUNCIATE > 1
    printf("writing namespaces for start element {http://www.bryansaunders.net/divelog}userAccount...\n");
#endif

    status = xmlTextWriterWriteAttribute(writer, BAD_CAST "xmlns:divelog", BAD_CAST "http://www.bryansaunders.net/divelog");
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("unable to write namespace attribute xmlns:divelog. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterWriteAttribute(writer, BAD_CAST "xmlns:dl_security", BAD_CAST "http://www.bryansaunders.net/divelog/security");
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("unable to write namespace attribute xmlns:dl_security. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

#if DEBUG_ENUNCIATE > 1
  printf("writing type {http://www.bryansaunders.net/divelog}userAccount for root element {http://www.bryansaunders.net/divelog}userAccount...\n");
#endif
  status = xmlTextWriterWriteDivelogUserAccountType(writer, _userAccount);
  if (status < 0) {
#if DEBUG_ENUNCIATE
    printf("unable to write type for start element {http://www.bryansaunders.net/divelog}userAccount. status: %i\n", status);
#endif
    return status;
  }
  totalBytes += status;

  status = xmlTextWriterEndElement(writer);
  if (status < 0) {
#if DEBUG_ENUNCIATE
    printf("unable to end element {http://www.bryansaunders.net/divelog}userAccount. status: %i\n", status);
#endif
    return status;
  }
  totalBytes += status;

  return totalBytes;
}

/**
 * Frees the children of a UserAccount.
 *
 * @param _userAccount The UserAccount whose children are to be free.
 */
static void freeDivelogUserAccountElement(struct novaglobalapi_divelog_userAccount *_userAccount) {
  freeDivelogUserAccountType(_userAccount);
}

/**
 * Reads a UserAccount from XML. The reader is assumed to be at the start element.
 *
 * @return the UserAccount, or NULL in case of error.
 */
static struct novaglobalapi_divelog_userAccount *xmlTextReaderReadDivelogUserAccountType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct novaglobalapi_divelog_userAccount *_userAccount = calloc(1, sizeof(struct novaglobalapi_divelog_userAccount));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeDivelogUserAccountType(_userAccount);
        free(_userAccount);
        return NULL;
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "id", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}id of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
        _child_accessor = xmlTextReaderReadXsIntType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}id of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogUserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->id = ((int*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "version", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}version of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
        _child_accessor = xmlTextReaderReadXsIntType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}version of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogUserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->version = ((int*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "created", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}created of type {http://www.w3.org/2001/XMLSchema}dateTime.\n");
#endif
        _child_accessor = xmlTextReaderReadXsDateTimeType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}created of type {http://www.w3.org/2001/XMLSchema}dateTime.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogUserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->created = ((struct tm*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "updated", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}updated of type {http://www.w3.org/2001/XMLSchema}dateTime.\n");
#endif
        _child_accessor = xmlTextReaderReadXsDateTimeType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}updated of type {http://www.w3.org/2001/XMLSchema}dateTime.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogUserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->updated = ((struct tm*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "firstName", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}firstName of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}firstName of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogUserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->firstName = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "lastName", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}lastName of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}lastName of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogUserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->lastName = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "country", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}country of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}country of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogUserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->country = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "state", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}state of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}state of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogUserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->state = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "city", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}city of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}city of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogUserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->city = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "email", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}email of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}email of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogUserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->email = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "password", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}password of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}password of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogUserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->password = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "roles", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}roles of type {http://www.bryansaunders.net/divelog/security}role.\n");
#endif
        _child_accessor = xmlTextReaderReadDl_securityRoleType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}roles of type {http://www.bryansaunders.net/divelog/security}role.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogUserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->roles = realloc(_userAccount->roles, (_userAccount->_sizeof_roles + 1) * sizeof(enum novaglobalapi_dl_security_role));
        memcpy(&(_userAccount->roles[_userAccount->_sizeof_roles++]), _child_accessor, sizeof(enum novaglobalapi_dl_security_role));
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "permissions", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}permissions of type {http://www.bryansaunders.net/divelog/security}permission.\n");
#endif
        _child_accessor = xmlTextReaderReadDl_securityPermissionType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}permissions of type {http://www.bryansaunders.net/divelog/security}permission.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogUserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->permissions = realloc(_userAccount->permissions, (_userAccount->_sizeof_permissions + 1) * sizeof(enum novaglobalapi_dl_security_permission));
        memcpy(&(_userAccount->permissions[_userAccount->_sizeof_permissions++]), _child_accessor, sizeof(enum novaglobalapi_dl_security_permission));
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "apiKey", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}apiKey of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}apiKey of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogUserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->apiKey = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "apiKeyExpiration", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}apiKeyExpiration of type {http://www.w3.org/2001/XMLSchema}dateTime.\n");
#endif
        _child_accessor = xmlTextReaderReadXsDateTimeType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}apiKeyExpiration of type {http://www.w3.org/2001/XMLSchema}dateTime.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDivelogUserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->apiKeyExpiration = ((struct tm*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {http://www.bryansaunders.net/divelog}userAccount.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {http://www.bryansaunders.net/divelog}userAccount. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
        }
#endif
        status = xmlTextReaderSkipElement(reader);
      }
    }
  }

  return _userAccount;
}

/**
 * Writes a UserAccount to XML.
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteDivelogUserAccountType(xmlTextWriterPtr writer, struct novaglobalapi_divelog_userAccount *_userAccount) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;
  if (_userAccount->id != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "id", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}id. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}int for element {}id...\n");
#endif
    status = xmlTextWriterWriteXsIntType(writer, (_userAccount->id));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}int for element {}id. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}id. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_userAccount->version != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "version", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}version. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}int for element {}version...\n");
#endif
    status = xmlTextWriterWriteXsIntType(writer, (_userAccount->version));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}int for element {}version. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}version. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_userAccount->created != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "created", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}created. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}dateTime for element {}created...\n");
#endif
    status = xmlTextWriterWriteXsDateTimeType(writer, (_userAccount->created));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}dateTime for element {}created. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}created. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_userAccount->updated != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "updated", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}updated. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}dateTime for element {}updated...\n");
#endif
    status = xmlTextWriterWriteXsDateTimeType(writer, (_userAccount->updated));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}dateTime for element {}updated. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}updated. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_userAccount->firstName != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "firstName", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}firstName. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}firstName...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_userAccount->firstName));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}firstName. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}firstName. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_userAccount->lastName != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "lastName", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}lastName. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}lastName...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_userAccount->lastName));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}lastName. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}lastName. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_userAccount->country != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "country", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}country. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}country...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_userAccount->country));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}country. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}country. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_userAccount->state != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "state", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}state. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}state...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_userAccount->state));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}state. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}state. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_userAccount->city != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "city", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}city. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}city...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_userAccount->city));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}city. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}city. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_userAccount->email != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "email", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}email. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}email...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_userAccount->email));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}email. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}email. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_userAccount->password != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "password", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}password. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}password...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_userAccount->password));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}password. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}password. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  for (i = 0; i < _userAccount->_sizeof_roles; i++) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "roles", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}roles. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.bryansaunders.net/divelog/security}role for element {}roles...\n");
#endif
    status = xmlTextWriterWriteDl_securityRoleType(writer, &(_userAccount->roles[i]));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.bryansaunders.net/divelog/security}role for element {}roles. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}roles. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  for (i = 0; i < _userAccount->_sizeof_permissions; i++) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "permissions", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}permissions. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.bryansaunders.net/divelog/security}permission for element {}permissions...\n");
#endif
    status = xmlTextWriterWriteDl_securityPermissionType(writer, &(_userAccount->permissions[i]));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.bryansaunders.net/divelog/security}permission for element {}permissions. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}permissions. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_userAccount->apiKey != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "apiKey", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}apiKey. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}apiKey...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_userAccount->apiKey));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}apiKey. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}apiKey. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_userAccount->apiKeyExpiration != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "apiKeyExpiration", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}apiKeyExpiration. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}dateTime for element {}apiKeyExpiration...\n");
#endif
    status = xmlTextWriterWriteXsDateTimeType(writer, (_userAccount->apiKeyExpiration));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}dateTime for element {}apiKeyExpiration. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}apiKeyExpiration. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

  return totalBytes;
}

/**
 * Frees the elements of a UserAccount.
 *
 * @param _userAccount The UserAccount to free.
 */
static void freeDivelogUserAccountType(struct novaglobalapi_divelog_userAccount *_userAccount) {
  int i;
  if (_userAccount->id != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor id of type novaglobalapi_divelog_userAccount...\n");
#endif
    freeXsIntType(_userAccount->id);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor id of type novaglobalapi_divelog_userAccount...\n");
#endif
    free(_userAccount->id);
  }
  if (_userAccount->version != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor version of type novaglobalapi_divelog_userAccount...\n");
#endif
    freeXsIntType(_userAccount->version);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor version of type novaglobalapi_divelog_userAccount...\n");
#endif
    free(_userAccount->version);
  }
  if (_userAccount->created != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor created of type novaglobalapi_divelog_userAccount...\n");
#endif
    freeXsDateTimeType(_userAccount->created);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor created of type novaglobalapi_divelog_userAccount...\n");
#endif
    free(_userAccount->created);
  }
  if (_userAccount->updated != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor updated of type novaglobalapi_divelog_userAccount...\n");
#endif
    freeXsDateTimeType(_userAccount->updated);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor updated of type novaglobalapi_divelog_userAccount...\n");
#endif
    free(_userAccount->updated);
  }
  if (_userAccount->firstName != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor firstName of type novaglobalapi_divelog_userAccount...\n");
#endif
    freeXsStringType(_userAccount->firstName);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor firstName of type novaglobalapi_divelog_userAccount...\n");
#endif
    free(_userAccount->firstName);
  }
  if (_userAccount->lastName != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor lastName of type novaglobalapi_divelog_userAccount...\n");
#endif
    freeXsStringType(_userAccount->lastName);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor lastName of type novaglobalapi_divelog_userAccount...\n");
#endif
    free(_userAccount->lastName);
  }
  if (_userAccount->country != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor country of type novaglobalapi_divelog_userAccount...\n");
#endif
    freeXsStringType(_userAccount->country);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor country of type novaglobalapi_divelog_userAccount...\n");
#endif
    free(_userAccount->country);
  }
  if (_userAccount->state != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor state of type novaglobalapi_divelog_userAccount...\n");
#endif
    freeXsStringType(_userAccount->state);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor state of type novaglobalapi_divelog_userAccount...\n");
#endif
    free(_userAccount->state);
  }
  if (_userAccount->city != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor city of type novaglobalapi_divelog_userAccount...\n");
#endif
    freeXsStringType(_userAccount->city);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor city of type novaglobalapi_divelog_userAccount...\n");
#endif
    free(_userAccount->city);
  }
  if (_userAccount->email != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor email of type novaglobalapi_divelog_userAccount...\n");
#endif
    freeXsStringType(_userAccount->email);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor email of type novaglobalapi_divelog_userAccount...\n");
#endif
    free(_userAccount->email);
  }
  if (_userAccount->password != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor password of type novaglobalapi_divelog_userAccount...\n");
#endif
    freeXsStringType(_userAccount->password);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor password of type novaglobalapi_divelog_userAccount...\n");
#endif
    free(_userAccount->password);
  }
  if (_userAccount->roles != NULL) {
    for (i = 0; i < _userAccount->_sizeof_roles; i++) {
#if DEBUG_ENUNCIATE > 1
      printf("Freeing accessor roles[%i] of type novaglobalapi_divelog_userAccount...\n", i);
#endif
      freeDl_securityRoleType(&(_userAccount->roles[i]));
    }
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor roles of type novaglobalapi_divelog_userAccount...\n");
#endif
    free(_userAccount->roles);
  }
  if (_userAccount->permissions != NULL) {
    for (i = 0; i < _userAccount->_sizeof_permissions; i++) {
#if DEBUG_ENUNCIATE > 1
      printf("Freeing accessor permissions[%i] of type novaglobalapi_divelog_userAccount...\n", i);
#endif
      freeDl_securityPermissionType(&(_userAccount->permissions[i]));
    }
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor permissions of type novaglobalapi_divelog_userAccount...\n");
#endif
    free(_userAccount->permissions);
  }
  if (_userAccount->apiKey != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor apiKey of type novaglobalapi_divelog_userAccount...\n");
#endif
    freeXsStringType(_userAccount->apiKey);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor apiKey of type novaglobalapi_divelog_userAccount...\n");
#endif
    free(_userAccount->apiKey);
  }
  if (_userAccount->apiKeyExpiration != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor apiKeyExpiration of type novaglobalapi_divelog_userAccount...\n");
#endif
    freeXsDateTimeType(_userAccount->apiKeyExpiration);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor apiKeyExpiration of type novaglobalapi_divelog_userAccount...\n");
#endif
    free(_userAccount->apiKeyExpiration);
  }
}
#endif /* DEF_novaglobalapi_divelog_userAccount_M */
#ifndef DEF_novaglobalapi_dl_security_credentials_M
#define DEF_novaglobalapi_dl_security_credentials_M

/**
 * Reads a Credentials element from XML. The element to be read is "{http://www.bryansaunders.net/divelog/security}credentials", and
 * it is assumed that the reader is pointing to the XML document (not the element).
 *
 * @param reader The XML reader.
 * @return The Credentials, or NULL in case of error.
 */
struct novaglobalapi_dl_security_credentials *xml_read_novaglobalapi_dl_security_credentials(xmlTextReaderPtr reader) {
  int status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
  return xmlTextReaderReadDl_securityCredentialsElement(reader);
}

/**
 * Writes a Credentials to XML under element name "{http://www.bryansaunders.net/divelog/security}credentials".
 *
 * @param writer The XML writer.
 * @param _credentials The Credentials to write.
 * @return 1 if successful, 0 otherwise.
 */
int xml_write_novaglobalapi_dl_security_credentials(xmlTextWriterPtr writer, struct novaglobalapi_dl_security_credentials *_credentials) {
  return xmlTextWriterWriteDl_securityCredentialsElementNS(writer, _credentials, 1);
}

/**
 * Frees a Credentials.
 *
 * @param _credentials The Credentials to free.
 */
void free_novaglobalapi_dl_security_credentials(struct novaglobalapi_dl_security_credentials *_credentials) {
  freeDl_securityCredentialsType(_credentials);
  free(_credentials);
}

/**
 * Reads a Credentials element from XML. The element to be read is "{http://www.bryansaunders.net/divelog/security}credentials", and
 * it is assumed that the reader is pointing to that element.
 *
 * @param reader The XML reader.
 * @return The Credentials, or NULL in case of error.
 */
struct novaglobalapi_dl_security_credentials *xmlTextReaderReadDl_securityCredentialsElement(xmlTextReaderPtr reader) {
  struct novaglobalapi_dl_security_credentials *_credentials = NULL;

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "credentials", xmlTextReaderConstLocalName(reader)) == 0
    && xmlStrcmp(BAD_CAST "http://www.bryansaunders.net/divelog/security", xmlTextReaderConstNamespaceUri(reader)) == 0) {
#if DEBUG_ENUNCIATE > 1
    printf("Attempting to read root element {http://www.bryansaunders.net/divelog/security}credentials.\n");
#endif
    _credentials = xmlTextReaderReadDl_securityCredentialsType(reader);
  }
#if DEBUG_ENUNCIATE
  if (_credentials == NULL) {
    if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
      printf("attempt to read {http://www.bryansaunders.net/divelog/security}credentials failed. current element: {}%s\n",  xmlTextReaderConstLocalName(reader));
    }
    else {
      printf("attempt to read {http://www.bryansaunders.net/divelog/security}credentials failed. current element: {%s}%s\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
    }
  }
#endif

  return _credentials;
}

/**
 * Writes a Credentials to XML under element name "{http://www.bryansaunders.net/divelog/security}credentials".
 * Does NOT write the namespace prefixes.
 *
 * @param writer The XML writer.
 * @param _credentials The Credentials to write.
 * @return 1 if successful, 0 otherwise.
 */
static int xmlTextWriterWriteDl_securityCredentialsElement(xmlTextWriterPtr writer, struct novaglobalapi_dl_security_credentials *_credentials) {
  return xmlTextWriterWriteDl_securityCredentialsElementNS(writer, _credentials, 0);
}

/**
 * Writes a Credentials to XML under element name "{http://www.bryansaunders.net/divelog/security}credentials".
 *
 * @param writer The XML writer.
 * @param _credentials The Credentials to write.
 * @param writeNamespaces Whether to write the namespace prefixes.
 * @return 1 if successful, 0 otherwise.
 */
static int xmlTextWriterWriteDl_securityCredentialsElementNS(xmlTextWriterPtr writer, struct novaglobalapi_dl_security_credentials *_credentials, int writeNamespaces) {
  int totalBytes = 0;
  int status;

  status = xmlTextWriterStartElementNS(writer, BAD_CAST "dl_security", BAD_CAST "credentials", NULL);
  if (status < 0) {
#if DEBUG_ENUNCIATE
    printf("unable to write start element {http://www.bryansaunders.net/divelog/security}credentials. status: %i\n", status);
#endif
    return status;
  }
  totalBytes += status;

  if (writeNamespaces) {
#if DEBUG_ENUNCIATE > 1
    printf("writing namespaces for start element {http://www.bryansaunders.net/divelog/security}credentials...\n");
#endif

    status = xmlTextWriterWriteAttribute(writer, BAD_CAST "xmlns:dl_security", BAD_CAST "http://www.bryansaunders.net/divelog/security");
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("unable to write namespace attribute xmlns:dl_security. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

#if DEBUG_ENUNCIATE > 1
  printf("writing type {http://www.bryansaunders.net/divelog/security}credentials for root element {http://www.bryansaunders.net/divelog/security}credentials...\n");
#endif
  status = xmlTextWriterWriteDl_securityCredentialsType(writer, _credentials);
  if (status < 0) {
#if DEBUG_ENUNCIATE
    printf("unable to write type for start element {http://www.bryansaunders.net/divelog/security}credentials. status: %i\n", status);
#endif
    return status;
  }
  totalBytes += status;

  status = xmlTextWriterEndElement(writer);
  if (status < 0) {
#if DEBUG_ENUNCIATE
    printf("unable to end element {http://www.bryansaunders.net/divelog/security}credentials. status: %i\n", status);
#endif
    return status;
  }
  totalBytes += status;

  return totalBytes;
}

/**
 * Frees the children of a Credentials.
 *
 * @param _credentials The Credentials whose children are to be free.
 */
static void freeDl_securityCredentialsElement(struct novaglobalapi_dl_security_credentials *_credentials) {
  freeDl_securityCredentialsType(_credentials);
}

/**
 * Reads a Credentials from XML. The reader is assumed to be at the start element.
 *
 * @return the Credentials, or NULL in case of error.
 */
static struct novaglobalapi_dl_security_credentials *xmlTextReaderReadDl_securityCredentialsType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct novaglobalapi_dl_security_credentials *_credentials = calloc(1, sizeof(struct novaglobalapi_dl_security_credentials));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeDl_securityCredentialsType(_credentials);
        free(_credentials);
        return NULL;
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "username", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}username of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}username of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDl_securityCredentialsType(_credentials);
          free(_credentials);
          return NULL;
        }

        _credentials->username = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "password", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}password of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}password of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeDl_securityCredentialsType(_credentials);
          free(_credentials);
          return NULL;
        }

        _credentials->password = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {http://www.bryansaunders.net/divelog/security}credentials.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {http://www.bryansaunders.net/divelog/security}credentials. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
        }
#endif
        status = xmlTextReaderSkipElement(reader);
      }
    }
  }

  return _credentials;
}

/**
 * Writes a Credentials to XML.
 *
 * @param writer The XML writer.
 * @param _credentials The Credentials to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteDl_securityCredentialsType(xmlTextWriterPtr writer, struct novaglobalapi_dl_security_credentials *_credentials) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;
  if (_credentials->username != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "username", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}username. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}username...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_credentials->username));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}username. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}username. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_credentials->password != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "password", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}password. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}password...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_credentials->password));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}password. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}password. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

  return totalBytes;
}

/**
 * Frees the elements of a Credentials.
 *
 * @param _credentials The Credentials to free.
 */
static void freeDl_securityCredentialsType(struct novaglobalapi_dl_security_credentials *_credentials) {
  int i;
  if (_credentials->username != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor username of type novaglobalapi_dl_security_credentials...\n");
#endif
    freeXsStringType(_credentials->username);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor username of type novaglobalapi_dl_security_credentials...\n");
#endif
    free(_credentials->username);
  }
  if (_credentials->password != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor password of type novaglobalapi_dl_security_credentials...\n");
#endif
    freeXsStringType(_credentials->password);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor password of type novaglobalapi_dl_security_credentials...\n");
#endif
    free(_credentials->password);
  }
}
#endif /* DEF_novaglobalapi_dl_security_credentials_M */
#ifndef DEF_novaglobalapi_dl_security_permission_M
#define DEF_novaglobalapi_dl_security_permission_M

/**
 * Reads a Permission from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The Permission, or NULL if unable to be read.
 */
static enum novaglobalapi_dl_security_permission *xmlTextReaderReadDl_securityPermissionType(xmlTextReaderPtr reader) {
  xmlChar *enumValue = xmlTextReaderReadEntireNodeValue(reader);
  enum novaglobalapi_dl_security_permission *value = calloc(1, sizeof(enum novaglobalapi_dl_security_permission));
  if (enumValue != NULL) {
    if (xmlStrcmp(enumValue, BAD_CAST "EDIT_SELF") == 0) {
      *value = NOVAGLOBALAPI_DL_SECURITY_PERMISSION_EDIT_SELF;
      free(enumValue);
      return value;
    }
    if (xmlStrcmp(enumValue, BAD_CAST "DELETE_SELF") == 0) {
      *value = NOVAGLOBALAPI_DL_SECURITY_PERMISSION_DELETE_SELF;
      free(enumValue);
      return value;
    }
#if DEBUG_ENUNCIATE
    printf("Attempt to read enum value failed: %s doesn't match an enum value.\n", enumValue);
#endif
  }
#if DEBUG_ENUNCIATE
  else {
    printf("Attempt to read enum value failed: NULL value.\n");
  }
#endif

  return NULL;
}

/**
 * Writes a Permission to XML.
 *
 * @param writer The XML writer.
 * @param _permission The Permission to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteDl_securityPermissionType(xmlTextWriterPtr writer, enum novaglobalapi_dl_security_permission *_permission) {
  switch (*_permission) {
    case NOVAGLOBALAPI_DL_SECURITY_PERMISSION_EDIT_SELF:
      return xmlTextWriterWriteString(writer, BAD_CAST "EDIT_SELF");
    case NOVAGLOBALAPI_DL_SECURITY_PERMISSION_DELETE_SELF:
      return xmlTextWriterWriteString(writer, BAD_CAST "DELETE_SELF");
  }

#if DEBUG_ENUNCIATE
  printf("Unable to write enum value (no valid value found).\n");
#endif
  return -1;
}

/**
 * Frees a Permission.
 *
 * @param _permission The Permission to free.
 */
static void freeDl_securityPermissionType(enum novaglobalapi_dl_security_permission *_permission) {
  //no-op
}
#endif /* DEF_novaglobalapi_dl_security_permission_M */
#ifndef DEF_novaglobalapi_dl_security_role_M
#define DEF_novaglobalapi_dl_security_role_M

/**
 * Reads a Role from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The Role, or NULL if unable to be read.
 */
static enum novaglobalapi_dl_security_role *xmlTextReaderReadDl_securityRoleType(xmlTextReaderPtr reader) {
  xmlChar *enumValue = xmlTextReaderReadEntireNodeValue(reader);
  enum novaglobalapi_dl_security_role *value = calloc(1, sizeof(enum novaglobalapi_dl_security_role));
  if (enumValue != NULL) {
    if (xmlStrcmp(enumValue, BAD_CAST "USER") == 0) {
      *value = NOVAGLOBALAPI_DL_SECURITY_ROLE_USER;
      free(enumValue);
      return value;
    }
    if (xmlStrcmp(enumValue, BAD_CAST "ADMIN") == 0) {
      *value = NOVAGLOBALAPI_DL_SECURITY_ROLE_ADMIN;
      free(enumValue);
      return value;
    }
#if DEBUG_ENUNCIATE
    printf("Attempt to read enum value failed: %s doesn't match an enum value.\n", enumValue);
#endif
  }
#if DEBUG_ENUNCIATE
  else {
    printf("Attempt to read enum value failed: NULL value.\n");
  }
#endif

  return NULL;
}

/**
 * Writes a Role to XML.
 *
 * @param writer The XML writer.
 * @param _role The Role to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteDl_securityRoleType(xmlTextWriterPtr writer, enum novaglobalapi_dl_security_role *_role) {
  switch (*_role) {
    case NOVAGLOBALAPI_DL_SECURITY_ROLE_USER:
      return xmlTextWriterWriteString(writer, BAD_CAST "USER");
    case NOVAGLOBALAPI_DL_SECURITY_ROLE_ADMIN:
      return xmlTextWriterWriteString(writer, BAD_CAST "ADMIN");
  }

#if DEBUG_ENUNCIATE
  printf("Unable to write enum value (no valid value found).\n");
#endif
  return -1;
}

/**
 * Frees a Role.
 *
 * @param _role The Role to free.
 */
static void freeDl_securityRoleType(enum novaglobalapi_dl_security_role *_role) {
  //no-op
}
#endif /* DEF_novaglobalapi_dl_security_role_M */
