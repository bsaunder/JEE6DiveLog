#include <enunciate-common.c>
#ifndef DEF_novaglobalapi_ns0_diveLogEntity_H
#define DEF_novaglobalapi_ns0_diveLogEntity_H

/**
 *  Base Entity Element for all DiveLog Entities.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
struct novaglobalapi_ns0_diveLogEntity {


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
static struct novaglobalapi_ns0_diveLogEntity *xmlTextReaderReadNs0DiveLogEntityType(xmlTextReaderPtr reader);

/**
 * Writes a DiveLogEntity to XML.
 *
 * @param writer The XML writer.
 * @param _diveLogEntity The DiveLogEntity to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0DiveLogEntityType(xmlTextWriterPtr writer, struct novaglobalapi_ns0_diveLogEntity *_diveLogEntity);

/**
 * Frees the elements of a DiveLogEntity.
 *
 * @param _diveLogEntity The DiveLogEntity to free.
 */
static void freeNs0DiveLogEntityType(struct novaglobalapi_ns0_diveLogEntity *_diveLogEntity);

#endif /* DEF_novaglobalapi_ns0_diveLogEntity_H */
#ifndef DEF_novaglobalapi_ns0_userAccount_H
#define DEF_novaglobalapi_ns0_userAccount_H

/**
 *  User Model.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
struct novaglobalapi_ns0_userAccount {


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
  enum novaglobalapi_ns0_role *roles;

  /**
   * Size of the roles array.
   */
  int _sizeof_roles;

  /**
   * the permissions
   */
  enum novaglobalapi_ns0_permission *permissions;

  /**
   * Size of the permissions array.
   */
  int _sizeof_permissions;
};

/**
 * Reads a UserAccount element from XML. The element to be read is "userAccount", and
 * it is assumed that the reader is pointing to the XML document (not the element).
 *
 * @param reader The XML reader.
 * @return The UserAccount, or NULL in case of error.
 */
struct novaglobalapi_ns0_userAccount *xml_read_novaglobalapi_ns0_userAccount(xmlTextReaderPtr reader);

/**
 * Writes a UserAccount to XML under element name "userAccount".
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
int xml_write_novaglobalapi_ns0_userAccount(xmlTextWriterPtr writer, struct novaglobalapi_ns0_userAccount *_userAccount);

/**
 * Frees a UserAccount.
 *
 * @param _userAccount The UserAccount to free.
 */
void free_novaglobalapi_ns0_userAccount(struct novaglobalapi_ns0_userAccount *_userAccount);

/**
 * Reads a UserAccount element from XML. The element to be read is "userAccount", and
 * it is assumed that the reader is already pointing to the element.
 *
 * @param reader The XML reader.
 * @return The UserAccount, or NULL in case of error.
 */
struct novaglobalapi_ns0_userAccount *xmlTextReaderReadNs0UserAccountElement(xmlTextReaderPtr reader);

/**
 * Writes a UserAccount to XML under element name "userAccount".
 * Does NOT write the namespace prefixes.
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0UserAccountElement(xmlTextWriterPtr writer, struct novaglobalapi_ns0_userAccount *_userAccount);

/**
 * Writes a UserAccount to XML under element name "userAccount".
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @param writeNamespaces Whether to write the namespace prefixes.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0UserAccountElementNS(xmlTextWriterPtr writer, struct novaglobalapi_ns0_userAccount *_userAccount, int writeNamespaces);

/**
 * Frees the children of a UserAccount.
 *
 * @param _userAccount The UserAccount whose children are to be free.
 */
static void freeNs0UserAccountElement(struct novaglobalapi_ns0_userAccount *_userAccount);

/**
 * Reads a UserAccount from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The UserAccount, or NULL in case of error.
 */
static struct novaglobalapi_ns0_userAccount *xmlTextReaderReadNs0UserAccountType(xmlTextReaderPtr reader);

/**
 * Writes a UserAccount to XML.
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0UserAccountType(xmlTextWriterPtr writer, struct novaglobalapi_ns0_userAccount *_userAccount);

/**
 * Frees the elements of a UserAccount.
 *
 * @param _userAccount The UserAccount to free.
 */
static void freeNs0UserAccountType(struct novaglobalapi_ns0_userAccount *_userAccount);

#endif /* DEF_novaglobalapi_ns0_userAccount_H */
#ifndef DEF_novaglobalapi_ns0_permission_H
#define DEF_novaglobalapi_ns0_permission_H

/**
 *  Account Permissions.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
enum novaglobalapi_ns0_permission {

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
static enum novaglobalapi_ns0_permission *xmlTextReaderReadNs0PermissionType(xmlTextReaderPtr reader);

/**
 * Writes a Permission to XML.
 *
 * @param writer The XML writer.
 * @param _permission The Permission to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0PermissionType(xmlTextWriterPtr writer, enum novaglobalapi_ns0_permission *_permission);

/**
 * Frees a Permission.
 *
 * @param _permission The Permission to free.
 */
static void freeNs0PermissionType(enum novaglobalapi_ns0_permission *_permission);

#endif
#ifndef DEF_novaglobalapi_ns0_role_H
#define DEF_novaglobalapi_ns0_role_H

/**
 *  Account Roles.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
enum novaglobalapi_ns0_role {

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
static enum novaglobalapi_ns0_role *xmlTextReaderReadNs0RoleType(xmlTextReaderPtr reader);

/**
 * Writes a Role to XML.
 *
 * @param writer The XML writer.
 * @param _role The Role to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0RoleType(xmlTextWriterPtr writer, enum novaglobalapi_ns0_role *_role);

/**
 * Frees a Role.
 *
 * @param _role The Role to free.
 */
static void freeNs0RoleType(enum novaglobalapi_ns0_role *_role);

#endif
#ifndef DEF_novaglobalapi_ns0_diveLogEntity_M
#define DEF_novaglobalapi_ns0_diveLogEntity_M

/**
 * Reads a DiveLogEntity from XML. The reader is assumed to be at the start element.
 *
 * @return the DiveLogEntity, or NULL in case of error.
 */
static struct novaglobalapi_ns0_diveLogEntity *xmlTextReaderReadNs0DiveLogEntityType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct novaglobalapi_ns0_diveLogEntity *_diveLogEntity = calloc(1, sizeof(struct novaglobalapi_ns0_diveLogEntity));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeNs0DiveLogEntityType(_diveLogEntity);
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
          freeNs0DiveLogEntityType(_diveLogEntity);
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
          freeNs0DiveLogEntityType(_diveLogEntity);
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
          freeNs0DiveLogEntityType(_diveLogEntity);
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
          freeNs0DiveLogEntityType(_diveLogEntity);
          free(_diveLogEntity);
          return NULL;
        }

        _diveLogEntity->updated = ((struct tm*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {}diveLogEntity.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {}diveLogEntity. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
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
static int xmlTextWriterWriteNs0DiveLogEntityType(xmlTextWriterPtr writer, struct novaglobalapi_ns0_diveLogEntity *_diveLogEntity) {
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
static void freeNs0DiveLogEntityType(struct novaglobalapi_ns0_diveLogEntity *_diveLogEntity) {
  int i;
  if (_diveLogEntity->id != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor id of type novaglobalapi_ns0_diveLogEntity...\n");
#endif
    freeXsIntType(_diveLogEntity->id);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor id of type novaglobalapi_ns0_diveLogEntity...\n");
#endif
    free(_diveLogEntity->id);
  }
  if (_diveLogEntity->version != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor version of type novaglobalapi_ns0_diveLogEntity...\n");
#endif
    freeXsIntType(_diveLogEntity->version);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor version of type novaglobalapi_ns0_diveLogEntity...\n");
#endif
    free(_diveLogEntity->version);
  }
  if (_diveLogEntity->created != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor created of type novaglobalapi_ns0_diveLogEntity...\n");
#endif
    freeXsDateTimeType(_diveLogEntity->created);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor created of type novaglobalapi_ns0_diveLogEntity...\n");
#endif
    free(_diveLogEntity->created);
  }
  if (_diveLogEntity->updated != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor updated of type novaglobalapi_ns0_diveLogEntity...\n");
#endif
    freeXsDateTimeType(_diveLogEntity->updated);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor updated of type novaglobalapi_ns0_diveLogEntity...\n");
#endif
    free(_diveLogEntity->updated);
  }
}
#endif /* DEF_novaglobalapi_ns0_diveLogEntity_M */
#ifndef DEF_novaglobalapi_ns0_userAccount_M
#define DEF_novaglobalapi_ns0_userAccount_M

/**
 * Reads a UserAccount element from XML. The element to be read is "userAccount", and
 * it is assumed that the reader is pointing to the XML document (not the element).
 *
 * @param reader The XML reader.
 * @return The UserAccount, or NULL in case of error.
 */
struct novaglobalapi_ns0_userAccount *xml_read_novaglobalapi_ns0_userAccount(xmlTextReaderPtr reader) {
  int status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
  return xmlTextReaderReadNs0UserAccountElement(reader);
}

/**
 * Writes a UserAccount to XML under element name "userAccount".
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @return 1 if successful, 0 otherwise.
 */
int xml_write_novaglobalapi_ns0_userAccount(xmlTextWriterPtr writer, struct novaglobalapi_ns0_userAccount *_userAccount) {
  return xmlTextWriterWriteNs0UserAccountElementNS(writer, _userAccount, 1);
}

/**
 * Frees a UserAccount.
 *
 * @param _userAccount The UserAccount to free.
 */
void free_novaglobalapi_ns0_userAccount(struct novaglobalapi_ns0_userAccount *_userAccount) {
  freeNs0UserAccountType(_userAccount);
  free(_userAccount);
}

/**
 * Reads a UserAccount element from XML. The element to be read is "userAccount", and
 * it is assumed that the reader is pointing to that element.
 *
 * @param reader The XML reader.
 * @return The UserAccount, or NULL in case of error.
 */
struct novaglobalapi_ns0_userAccount *xmlTextReaderReadNs0UserAccountElement(xmlTextReaderPtr reader) {
  struct novaglobalapi_ns0_userAccount *_userAccount = NULL;

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "userAccount", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Attempting to read root element {}userAccount.\n");
#endif
    _userAccount = xmlTextReaderReadNs0UserAccountType(reader);
  }
#if DEBUG_ENUNCIATE
  if (_userAccount == NULL) {
    if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
      printf("attempt to read {}userAccount failed. current element: {}%s\n",  xmlTextReaderConstLocalName(reader));
    }
    else {
      printf("attempt to read {}userAccount failed. current element: {%s}%s\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
    }
  }
#endif

  return _userAccount;
}

/**
 * Writes a UserAccount to XML under element name "userAccount".
 * Does NOT write the namespace prefixes.
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @return 1 if successful, 0 otherwise.
 */
static int xmlTextWriterWriteNs0UserAccountElement(xmlTextWriterPtr writer, struct novaglobalapi_ns0_userAccount *_userAccount) {
  return xmlTextWriterWriteNs0UserAccountElementNS(writer, _userAccount, 0);
}

/**
 * Writes a UserAccount to XML under element name "userAccount".
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @param writeNamespaces Whether to write the namespace prefixes.
 * @return 1 if successful, 0 otherwise.
 */
static int xmlTextWriterWriteNs0UserAccountElementNS(xmlTextWriterPtr writer, struct novaglobalapi_ns0_userAccount *_userAccount, int writeNamespaces) {
  int totalBytes = 0;
  int status;

  status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "userAccount", NULL);
  if (status < 0) {
#if DEBUG_ENUNCIATE
    printf("unable to write start element {}userAccount. status: %i\n", status);
#endif
    return status;
  }
  totalBytes += status;

#if DEBUG_ENUNCIATE > 1
  printf("writing type {}userAccount for root element {}userAccount...\n");
#endif
  status = xmlTextWriterWriteNs0UserAccountType(writer, _userAccount);
  if (status < 0) {
#if DEBUG_ENUNCIATE
    printf("unable to write type for start element {}userAccount. status: %i\n", status);
#endif
    return status;
  }
  totalBytes += status;

  status = xmlTextWriterEndElement(writer);
  if (status < 0) {
#if DEBUG_ENUNCIATE
    printf("unable to end element {}userAccount. status: %i\n", status);
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
static void freeNs0UserAccountElement(struct novaglobalapi_ns0_userAccount *_userAccount) {
  freeNs0UserAccountType(_userAccount);
}

/**
 * Reads a UserAccount from XML. The reader is assumed to be at the start element.
 *
 * @return the UserAccount, or NULL in case of error.
 */
static struct novaglobalapi_ns0_userAccount *xmlTextReaderReadNs0UserAccountType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct novaglobalapi_ns0_userAccount *_userAccount = calloc(1, sizeof(struct novaglobalapi_ns0_userAccount));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeNs0UserAccountType(_userAccount);
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
          freeNs0UserAccountType(_userAccount);
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
          freeNs0UserAccountType(_userAccount);
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
          freeNs0UserAccountType(_userAccount);
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
          freeNs0UserAccountType(_userAccount);
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
          freeNs0UserAccountType(_userAccount);
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
          freeNs0UserAccountType(_userAccount);
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
          freeNs0UserAccountType(_userAccount);
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
          freeNs0UserAccountType(_userAccount);
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
          freeNs0UserAccountType(_userAccount);
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
          freeNs0UserAccountType(_userAccount);
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
          freeNs0UserAccountType(_userAccount);
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
        printf("Attempting to read choice {}roles of type {}role.\n");
#endif
        _child_accessor = xmlTextReaderReadNs0RoleType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}roles of type {}role.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0UserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->roles = realloc(_userAccount->roles, (_userAccount->_sizeof_roles + 1) * sizeof(enum novaglobalapi_ns0_role));
        memcpy(&(_userAccount->roles[_userAccount->_sizeof_roles++]), _child_accessor, sizeof(enum novaglobalapi_ns0_role));
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "permissions", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}permissions of type {}permission.\n");
#endif
        _child_accessor = xmlTextReaderReadNs0PermissionType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}permissions of type {}permission.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0UserAccountType(_userAccount);
          free(_userAccount);
          return NULL;
        }

        _userAccount->permissions = realloc(_userAccount->permissions, (_userAccount->_sizeof_permissions + 1) * sizeof(enum novaglobalapi_ns0_permission));
        memcpy(&(_userAccount->permissions[_userAccount->_sizeof_permissions++]), _child_accessor, sizeof(enum novaglobalapi_ns0_permission));
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {}userAccount.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {}userAccount. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
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
static int xmlTextWriterWriteNs0UserAccountType(xmlTextWriterPtr writer, struct novaglobalapi_ns0_userAccount *_userAccount) {
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
    printf("writing type {}role for element {}roles...\n");
#endif
    status = xmlTextWriterWriteNs0RoleType(writer, &(_userAccount->roles[i]));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {}role for element {}roles. status: %i\n", status);
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
    printf("writing type {}permission for element {}permissions...\n");
#endif
    status = xmlTextWriterWriteNs0PermissionType(writer, &(_userAccount->permissions[i]));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {}permission for element {}permissions. status: %i\n", status);
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

  return totalBytes;
}

/**
 * Frees the elements of a UserAccount.
 *
 * @param _userAccount The UserAccount to free.
 */
static void freeNs0UserAccountType(struct novaglobalapi_ns0_userAccount *_userAccount) {
  int i;
  if (_userAccount->id != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor id of type novaglobalapi_ns0_userAccount...\n");
#endif
    freeXsIntType(_userAccount->id);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor id of type novaglobalapi_ns0_userAccount...\n");
#endif
    free(_userAccount->id);
  }
  if (_userAccount->version != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor version of type novaglobalapi_ns0_userAccount...\n");
#endif
    freeXsIntType(_userAccount->version);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor version of type novaglobalapi_ns0_userAccount...\n");
#endif
    free(_userAccount->version);
  }
  if (_userAccount->created != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor created of type novaglobalapi_ns0_userAccount...\n");
#endif
    freeXsDateTimeType(_userAccount->created);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor created of type novaglobalapi_ns0_userAccount...\n");
#endif
    free(_userAccount->created);
  }
  if (_userAccount->updated != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor updated of type novaglobalapi_ns0_userAccount...\n");
#endif
    freeXsDateTimeType(_userAccount->updated);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor updated of type novaglobalapi_ns0_userAccount...\n");
#endif
    free(_userAccount->updated);
  }
  if (_userAccount->firstName != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor firstName of type novaglobalapi_ns0_userAccount...\n");
#endif
    freeXsStringType(_userAccount->firstName);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor firstName of type novaglobalapi_ns0_userAccount...\n");
#endif
    free(_userAccount->firstName);
  }
  if (_userAccount->lastName != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor lastName of type novaglobalapi_ns0_userAccount...\n");
#endif
    freeXsStringType(_userAccount->lastName);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor lastName of type novaglobalapi_ns0_userAccount...\n");
#endif
    free(_userAccount->lastName);
  }
  if (_userAccount->country != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor country of type novaglobalapi_ns0_userAccount...\n");
#endif
    freeXsStringType(_userAccount->country);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor country of type novaglobalapi_ns0_userAccount...\n");
#endif
    free(_userAccount->country);
  }
  if (_userAccount->state != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor state of type novaglobalapi_ns0_userAccount...\n");
#endif
    freeXsStringType(_userAccount->state);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor state of type novaglobalapi_ns0_userAccount...\n");
#endif
    free(_userAccount->state);
  }
  if (_userAccount->city != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor city of type novaglobalapi_ns0_userAccount...\n");
#endif
    freeXsStringType(_userAccount->city);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor city of type novaglobalapi_ns0_userAccount...\n");
#endif
    free(_userAccount->city);
  }
  if (_userAccount->email != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor email of type novaglobalapi_ns0_userAccount...\n");
#endif
    freeXsStringType(_userAccount->email);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor email of type novaglobalapi_ns0_userAccount...\n");
#endif
    free(_userAccount->email);
  }
  if (_userAccount->password != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor password of type novaglobalapi_ns0_userAccount...\n");
#endif
    freeXsStringType(_userAccount->password);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor password of type novaglobalapi_ns0_userAccount...\n");
#endif
    free(_userAccount->password);
  }
  if (_userAccount->roles != NULL) {
    for (i = 0; i < _userAccount->_sizeof_roles; i++) {
#if DEBUG_ENUNCIATE > 1
      printf("Freeing accessor roles[%i] of type novaglobalapi_ns0_userAccount...\n", i);
#endif
      freeNs0RoleType(&(_userAccount->roles[i]));
    }
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor roles of type novaglobalapi_ns0_userAccount...\n");
#endif
    free(_userAccount->roles);
  }
  if (_userAccount->permissions != NULL) {
    for (i = 0; i < _userAccount->_sizeof_permissions; i++) {
#if DEBUG_ENUNCIATE > 1
      printf("Freeing accessor permissions[%i] of type novaglobalapi_ns0_userAccount...\n", i);
#endif
      freeNs0PermissionType(&(_userAccount->permissions[i]));
    }
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor permissions of type novaglobalapi_ns0_userAccount...\n");
#endif
    free(_userAccount->permissions);
  }
}
#endif /* DEF_novaglobalapi_ns0_userAccount_M */
#ifndef DEF_novaglobalapi_ns0_permission_M
#define DEF_novaglobalapi_ns0_permission_M

/**
 * Reads a Permission from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The Permission, or NULL if unable to be read.
 */
static enum novaglobalapi_ns0_permission *xmlTextReaderReadNs0PermissionType(xmlTextReaderPtr reader) {
  xmlChar *enumValue = xmlTextReaderReadEntireNodeValue(reader);
  enum novaglobalapi_ns0_permission *value = calloc(1, sizeof(enum novaglobalapi_ns0_permission));
  if (enumValue != NULL) {
    if (xmlStrcmp(enumValue, BAD_CAST "EDIT_SELF") == 0) {
      *value = NOVAGLOBALAPI_NS0_PERMISSION_EDIT_SELF;
      free(enumValue);
      return value;
    }
    if (xmlStrcmp(enumValue, BAD_CAST "DELETE_SELF") == 0) {
      *value = NOVAGLOBALAPI_NS0_PERMISSION_DELETE_SELF;
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
static int xmlTextWriterWriteNs0PermissionType(xmlTextWriterPtr writer, enum novaglobalapi_ns0_permission *_permission) {
  switch (*_permission) {
    case NOVAGLOBALAPI_NS0_PERMISSION_EDIT_SELF:
      return xmlTextWriterWriteString(writer, BAD_CAST "EDIT_SELF");
    case NOVAGLOBALAPI_NS0_PERMISSION_DELETE_SELF:
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
static void freeNs0PermissionType(enum novaglobalapi_ns0_permission *_permission) {
  //no-op
}
#endif /* DEF_novaglobalapi_ns0_permission_M */
#ifndef DEF_novaglobalapi_ns0_role_M
#define DEF_novaglobalapi_ns0_role_M

/**
 * Reads a Role from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The Role, or NULL if unable to be read.
 */
static enum novaglobalapi_ns0_role *xmlTextReaderReadNs0RoleType(xmlTextReaderPtr reader) {
  xmlChar *enumValue = xmlTextReaderReadEntireNodeValue(reader);
  enum novaglobalapi_ns0_role *value = calloc(1, sizeof(enum novaglobalapi_ns0_role));
  if (enumValue != NULL) {
    if (xmlStrcmp(enumValue, BAD_CAST "USER") == 0) {
      *value = NOVAGLOBALAPI_NS0_ROLE_USER;
      free(enumValue);
      return value;
    }
    if (xmlStrcmp(enumValue, BAD_CAST "ADMIN") == 0) {
      *value = NOVAGLOBALAPI_NS0_ROLE_ADMIN;
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
static int xmlTextWriterWriteNs0RoleType(xmlTextWriterPtr writer, enum novaglobalapi_ns0_role *_role) {
  switch (*_role) {
    case NOVAGLOBALAPI_NS0_ROLE_USER:
      return xmlTextWriterWriteString(writer, BAD_CAST "USER");
    case NOVAGLOBALAPI_NS0_ROLE_ADMIN:
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
static void freeNs0RoleType(enum novaglobalapi_ns0_role *_role) {
  //no-op
}
#endif /* DEF_novaglobalapi_ns0_role_M */
