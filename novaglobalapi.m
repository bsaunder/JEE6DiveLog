#import "novaglobalapi.h"
#ifndef DEF_NOVAGLOBALAPINS0DiveLogEntity_M
#define DEF_NOVAGLOBALAPINS0DiveLogEntity_M

/**
 *  Base Entity Element for all DiveLog Entities.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
@implementation NOVAGLOBALAPINS0DiveLogEntity

/**
 * the id
 */
- (int *) identifier
{
  return _identifier;
}

/**
 * the id
 */
- (void) setIdentifier: (int *) newIdentifier
{
  if (_identifier != NULL) {
    free(_identifier);
  }
  _identifier = newIdentifier;
}

/**
 * the version
 */
- (int *) version
{
  return _version;
}

/**
 * the version
 */
- (void) setVersion: (int *) newVersion
{
  if (_version != NULL) {
    free(_version);
  }
  _version = newVersion;
}

/**
 * the created
 */
- (NSDate *) created
{
  return _created;
}

/**
 * the created
 */
- (void) setCreated: (NSDate *) newCreated
{
  [newCreated retain];
  [_created release];
  _created = newCreated;
}

/**
 * the updated
 */
- (NSDate *) updated
{
  return _updated;
}

/**
 * the updated
 */
- (void) setUpdated: (NSDate *) newUpdated
{
  [newUpdated retain];
  [_updated release];
  _updated = newUpdated;
}

- (void) dealloc
{
  [self setIdentifier: NULL];
  [self setVersion: NULL];
  [self setCreated: nil];
  [self setUpdated: nil];
  [super dealloc];
}
@end /* implementation NOVAGLOBALAPINS0DiveLogEntity */

/**
 * Internal, private interface for JAXB reading and writing.
 */
@interface NOVAGLOBALAPINS0DiveLogEntity (JAXB) <JAXBReading, JAXBWriting, JAXBType>

@end /*interface NOVAGLOBALAPINS0DiveLogEntity (JAXB)*/

/**
 * Internal, private implementation for JAXB reading and writing.
 */
@implementation NOVAGLOBALAPINS0DiveLogEntity (JAXB)

/**
 * Read an instance of NOVAGLOBALAPINS0DiveLogEntity from an XML reader.
 *
 * @param reader The reader.
 * @return An instance of NOVAGLOBALAPINS0DiveLogEntity defined by the XML reader.
 */
+ (id<JAXBType>) readXMLType: (xmlTextReaderPtr) reader
{
  NOVAGLOBALAPINS0DiveLogEntity *_nOVAGLOBALAPINS0DiveLogEntity = [[NOVAGLOBALAPINS0DiveLogEntity alloc] init];
  NS_DURING
  {
    [_nOVAGLOBALAPINS0DiveLogEntity initWithReader: reader];
  }
  NS_HANDLER
  {
    _nOVAGLOBALAPINS0DiveLogEntity = nil;
    [localException raise];
  }
  NS_ENDHANDLER

  [_nOVAGLOBALAPINS0DiveLogEntity autorelease];
  return _nOVAGLOBALAPINS0DiveLogEntity;
}

/**
 * Initialize this instance of NOVAGLOBALAPINS0DiveLogEntity according to
 * the XML being read from the reader.
 *
 * @param reader The reader.
 */
- (id) initWithReader: (xmlTextReaderPtr) reader
{
  return [super initWithReader: reader];
}

/**
 * Write the XML for this instance of NOVAGLOBALAPINS0DiveLogEntity to the writer.
 * Note that since we're only writing the XML type,
 * No start/end element will be written.
 *
 * @param reader The reader.
 */
- (void) writeXMLType: (xmlTextWriterPtr) writer
{
  [super writeXMLType:writer];
}

//documentation inherited.
- (BOOL) readJAXBAttribute: (xmlTextReaderPtr) reader
{
  void *_child_accessor;

  if ([super readJAXBAttribute: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (BOOL) readJAXBValue: (xmlTextReaderPtr) reader
{
  return [super readJAXBValue: reader];
}

//documentation inherited.
- (BOOL) readJAXBChildElement: (xmlTextReaderPtr) reader
{
  id __child;
  void *_child_accessor;
  int status, depth;

  if ([super readJAXBChildElement: reader]) {
    return YES;
  }

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "id", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadIntType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setIdentifier: ((int*) _child_accessor)];
    return YES;
  }

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "version", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadIntType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setVersion: ((int*) _child_accessor)];
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "created", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}created of type {http://www.w3.org/2001/XMLSchema}dateTime.");
#endif
    __child = [NSDate readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}created of type {http://www.w3.org/2001/XMLSchema}dateTime.");
#endif

    [self setCreated: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "updated", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}updated of type {http://www.w3.org/2001/XMLSchema}dateTime.");
#endif
    __child = [NSDate readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}updated of type {http://www.w3.org/2001/XMLSchema}dateTime.");
#endif

    [self setUpdated: __child];
    return YES;
  } //end "if choice"


  return NO;
}

//documentation inherited.
- (int) readUnknownJAXBChildElement: (xmlTextReaderPtr) reader
{
  return [super readUnknownJAXBChildElement: reader];
}

//documentation inherited.
- (void) readUnknownJAXBAttribute: (xmlTextReaderPtr) reader
{
  [super readUnknownJAXBAttribute: reader];
}

//documentation inherited.
- (void) writeJAXBAttributes: (xmlTextWriterPtr) writer
{
  int status;

  [super writeJAXBAttributes: writer];

}

//documentation inherited.
- (void) writeJAXBValue: (xmlTextWriterPtr) writer
{
  [super writeJAXBValue: writer];
}

/**
 * Method for writing the child elements.
 *
 * @param writer The writer.
 */
- (void) writeJAXBChildElements: (xmlTextWriterPtr) writer
{
  int status;
  id __item;
  NSEnumerator *__enumerator;

  [super writeJAXBChildElements: writer];

  if ([self identifier] != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "id", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}id."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}id...");
#endif
    status = xmlTextWriterWriteIntType(writer, [self identifier]);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}id...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}id."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}id."];
    }
  }
  if ([self version] != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "version", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}version."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}version...");
#endif
    status = xmlTextWriterWriteIntType(writer, [self version]);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}version...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}version."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}version."];
    }
  }
  if ([self created]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "created", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}created."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}created...");
#endif
    [[self created] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}created...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}created."];
    }
  }
  if ([self updated]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "updated", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}updated."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}updated...");
#endif
    [[self updated] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}updated...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}updated."];
    }
  }
}
@end /* implementation NOVAGLOBALAPINS0DiveLogEntity (JAXB) */

#endif /* DEF_NOVAGLOBALAPINS0DiveLogEntity_M */
#ifndef DEF_NOVAGLOBALAPINS0Role_M
#define DEF_NOVAGLOBALAPINS0Role_M

/**
 * Reads a Role from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The Role, or NULL if unable to be read.
 */
static enum NOVAGLOBALAPINS0Role *xmlTextReaderReadNOVAGLOBALAPINS0RoleType(xmlTextReaderPtr reader)
{
  xmlChar *enumValue = xmlTextReaderReadEntireNodeValue(reader);
  enum NOVAGLOBALAPINS0Role *value = calloc(1, sizeof(enum NOVAGLOBALAPINS0Role));
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
    NSLog(@"Attempt to read enum value failed: %s doesn't match an enum value.", enumValue);
#endif
  }
#if DEBUG_ENUNCIATE
  else {
    NSLog(@"Attempt to read enum value failed: NULL value.");
  }
#endif

  return NULL;
}

/**
 * Utility method for getting the enum value for a string.
 *
 * @param _role The string to format.
 * @return The enum value or NULL on error.
 */
static enum NOVAGLOBALAPINS0Role *formatStringToNOVAGLOBALAPINS0RoleType(NSString *_role)
{
  enum NOVAGLOBALAPINS0Role *value = calloc(1, sizeof(enum NOVAGLOBALAPINS0Role));
  value = NULL;
  if ([@"USER" isEqualToString:_role]) {
    *value = NOVAGLOBALAPI_NS0_ROLE_USER;
  }
  if ([@"ADMIN" isEqualToString:_role]) {
    *value = NOVAGLOBALAPI_NS0_ROLE_ADMIN;
  }
#if DEBUG_ENUNCIATE
  NSLog(@"Attempt to read enum value failed: %s doesn't match an enum value.", _role);
#endif

  return value;
}

/**
 * Writes a Role to XML.
 *
 * @param writer The XML writer.
 * @param _role The Role to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNOVAGLOBALAPINS0RoleType(xmlTextWriterPtr writer, enum NOVAGLOBALAPINS0Role *_role)
{
  switch (*_role) {
    case NOVAGLOBALAPI_NS0_ROLE_USER:
      return xmlTextWriterWriteString(writer, BAD_CAST "USER");
    case NOVAGLOBALAPI_NS0_ROLE_ADMIN:
      return xmlTextWriterWriteString(writer, BAD_CAST "ADMIN");
  }

#if DEBUG_ENUNCIATE
  NSLog(@"Unable to write enum value (no valid value found).");
#endif
  return -1;
}

/**
 * Utility method for getting the string value of Role.
 *
 * @param _role The Role to format.
 * @return The string value or NULL on error.
 */
static NSString *formatNOVAGLOBALAPINS0RoleTypeToString(enum NOVAGLOBALAPINS0Role *_role)
{
  switch (*_role) {
    case NOVAGLOBALAPI_NS0_ROLE_USER:
      return @"USER";
    case NOVAGLOBALAPI_NS0_ROLE_ADMIN:
      return @"ADMIN";
    default:
      return NULL;
  }

  return NULL;
}
#endif /* DEF_NOVAGLOBALAPINS0Role_M */
#ifndef DEF_NOVAGLOBALAPINS0Permission_M
#define DEF_NOVAGLOBALAPINS0Permission_M

/**
 * Reads a Permission from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The Permission, or NULL if unable to be read.
 */
static enum NOVAGLOBALAPINS0Permission *xmlTextReaderReadNOVAGLOBALAPINS0PermissionType(xmlTextReaderPtr reader)
{
  xmlChar *enumValue = xmlTextReaderReadEntireNodeValue(reader);
  enum NOVAGLOBALAPINS0Permission *value = calloc(1, sizeof(enum NOVAGLOBALAPINS0Permission));
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
    NSLog(@"Attempt to read enum value failed: %s doesn't match an enum value.", enumValue);
#endif
  }
#if DEBUG_ENUNCIATE
  else {
    NSLog(@"Attempt to read enum value failed: NULL value.");
  }
#endif

  return NULL;
}

/**
 * Utility method for getting the enum value for a string.
 *
 * @param _permission The string to format.
 * @return The enum value or NULL on error.
 */
static enum NOVAGLOBALAPINS0Permission *formatStringToNOVAGLOBALAPINS0PermissionType(NSString *_permission)
{
  enum NOVAGLOBALAPINS0Permission *value = calloc(1, sizeof(enum NOVAGLOBALAPINS0Permission));
  value = NULL;
  if ([@"EDIT_SELF" isEqualToString:_permission]) {
    *value = NOVAGLOBALAPI_NS0_PERMISSION_EDIT_SELF;
  }
  if ([@"DELETE_SELF" isEqualToString:_permission]) {
    *value = NOVAGLOBALAPI_NS0_PERMISSION_DELETE_SELF;
  }
#if DEBUG_ENUNCIATE
  NSLog(@"Attempt to read enum value failed: %s doesn't match an enum value.", _permission);
#endif

  return value;
}

/**
 * Writes a Permission to XML.
 *
 * @param writer The XML writer.
 * @param _permission The Permission to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNOVAGLOBALAPINS0PermissionType(xmlTextWriterPtr writer, enum NOVAGLOBALAPINS0Permission *_permission)
{
  switch (*_permission) {
    case NOVAGLOBALAPI_NS0_PERMISSION_EDIT_SELF:
      return xmlTextWriterWriteString(writer, BAD_CAST "EDIT_SELF");
    case NOVAGLOBALAPI_NS0_PERMISSION_DELETE_SELF:
      return xmlTextWriterWriteString(writer, BAD_CAST "DELETE_SELF");
  }

#if DEBUG_ENUNCIATE
  NSLog(@"Unable to write enum value (no valid value found).");
#endif
  return -1;
}

/**
 * Utility method for getting the string value of Permission.
 *
 * @param _permission The Permission to format.
 * @return The string value or NULL on error.
 */
static NSString *formatNOVAGLOBALAPINS0PermissionTypeToString(enum NOVAGLOBALAPINS0Permission *_permission)
{
  switch (*_permission) {
    case NOVAGLOBALAPI_NS0_PERMISSION_EDIT_SELF:
      return @"EDIT_SELF";
    case NOVAGLOBALAPI_NS0_PERMISSION_DELETE_SELF:
      return @"DELETE_SELF";
    default:
      return NULL;
  }

  return NULL;
}
#endif /* DEF_NOVAGLOBALAPINS0Permission_M */
#ifndef DEF_NOVAGLOBALAPINS0UserAccount_M
#define DEF_NOVAGLOBALAPINS0UserAccount_M

/**
 *  User Model.
 
 @author Bryan Saunders <btsaunde@gmail.com>
 

 */
@implementation NOVAGLOBALAPINS0UserAccount

/**
 * the firstName
 */
- (NSString *) firstName
{
  return _firstName;
}

/**
 * the firstName
 */
- (void) setFirstName: (NSString *) newFirstName
{
  [newFirstName retain];
  [_firstName release];
  _firstName = newFirstName;
}

/**
 * the lastName
 */
- (NSString *) lastName
{
  return _lastName;
}

/**
 * the lastName
 */
- (void) setLastName: (NSString *) newLastName
{
  [newLastName retain];
  [_lastName release];
  _lastName = newLastName;
}

/**
 * the country
 */
- (NSString *) country
{
  return _country;
}

/**
 * the country
 */
- (void) setCountry: (NSString *) newCountry
{
  [newCountry retain];
  [_country release];
  _country = newCountry;
}

/**
 * the state
 */
- (NSString *) state
{
  return _state;
}

/**
 * the state
 */
- (void) setState: (NSString *) newState
{
  [newState retain];
  [_state release];
  _state = newState;
}

/**
 * the city
 */
- (NSString *) city
{
  return _city;
}

/**
 * the city
 */
- (void) setCity: (NSString *) newCity
{
  [newCity retain];
  [_city release];
  _city = newCity;
}

/**
 * the email
 */
- (NSString *) email
{
  return _email;
}

/**
 * the email
 */
- (void) setEmail: (NSString *) newEmail
{
  [newEmail retain];
  [_email release];
  _email = newEmail;
}

/**
 * the password
 */
- (NSString *) password
{
  return _password;
}

/**
 * the password
 */
- (void) setPassword: (NSString *) newPassword
{
  [newPassword retain];
  [_password release];
  _password = newPassword;
}

/**
 * the roles
 */
- (NSArray *) roles
{
  return _roles;
}

/**
 * the roles
 */
- (void) setRoles: (NSArray *) newRoles
{
  [newRoles retain];
  [_roles release];
  _roles = newRoles;
}

/**
 * the permissions
 */
- (NSArray *) permissions
{
  return _permissions;
}

/**
 * the permissions
 */
- (void) setPermissions: (NSArray *) newPermissions
{
  [newPermissions retain];
  [_permissions release];
  _permissions = newPermissions;
}

- (void) dealloc
{
  [self setFirstName: nil];
  [self setLastName: nil];
  [self setCountry: nil];
  [self setState: nil];
  [self setCity: nil];
  [self setEmail: nil];
  [self setPassword: nil];
  [self setRoles: nil];
  [self setPermissions: nil];
  [super dealloc];
}

//documentation inherited.
+ (id<EnunciateXML>) readFromXML: (NSData *) xml
{
  NOVAGLOBALAPINS0UserAccount *_nOVAGLOBALAPINS0UserAccount;
  xmlTextReaderPtr reader = xmlReaderForMemory([xml bytes], [xml length], NULL, NULL, 0);
  if (reader == NULL) {
    [NSException raise: @"XMLReadError"
                 format: @"Error instantiating an XML reader."];
    return nil;
  }

  _nOVAGLOBALAPINS0UserAccount = (NOVAGLOBALAPINS0UserAccount *) [NOVAGLOBALAPINS0UserAccount readXMLElement: reader];
  xmlFreeTextReader(reader); //free the reader
  return _nOVAGLOBALAPINS0UserAccount;
}

//documentation inherited.
- (NSData *) writeToXML
{
  xmlBufferPtr buf;
  xmlTextWriterPtr writer;
  int rc;
  NSData *data;

  buf = xmlBufferCreate();
  if (buf == NULL) {
    [NSException raise: @"XMLWriteError"
                 format: @"Error creating an XML buffer."];
    return nil;
  }

  writer = xmlNewTextWriterMemory(buf, 0);
  if (writer == NULL) {
    xmlBufferFree(buf);
    [NSException raise: @"XMLWriteError"
                 format: @"Error creating an XML writer."];
    return nil;
  }

  rc = xmlTextWriterStartDocument(writer, NULL, "utf-8", NULL);
  if (rc < 0) {
    xmlFreeTextWriter(writer);
    xmlBufferFree(buf);
    [NSException raise: @"XMLWriteError"
                 format: @"Error writing XML start document."];
    return nil;
  }

  NS_DURING
  {
    [self writeXMLElement: writer];
  }
  NS_HANDLER
  {
    xmlFreeTextWriter(writer);
    xmlBufferFree(buf);
    [localException raise];
  }
  NS_ENDHANDLER

  rc = xmlTextWriterEndDocument(writer);
  if (rc < 0) {
    xmlFreeTextWriter(writer);
    xmlBufferFree(buf);
    [NSException raise: @"XMLWriteError"
                 format: @"Error writing XML end document."];
    return nil;
  }

  xmlFreeTextWriter(writer);
  data = [NSData dataWithBytes: buf->content length: buf->use];
  xmlBufferFree(buf);
  return data;
}
@end /* implementation NOVAGLOBALAPINS0UserAccount */

/**
 * Internal, private interface for JAXB reading and writing.
 */
@interface NOVAGLOBALAPINS0UserAccount (JAXB) <JAXBReading, JAXBWriting, JAXBType, JAXBElement>

@end /*interface NOVAGLOBALAPINS0UserAccount (JAXB)*/

/**
 * Internal, private implementation for JAXB reading and writing.
 */
@implementation NOVAGLOBALAPINS0UserAccount (JAXB)

/**
 * Read an instance of NOVAGLOBALAPINS0UserAccount from an XML reader.
 *
 * @param reader The reader.
 * @return An instance of NOVAGLOBALAPINS0UserAccount defined by the XML reader.
 */
+ (id<JAXBType>) readXMLType: (xmlTextReaderPtr) reader
{
  NOVAGLOBALAPINS0UserAccount *_nOVAGLOBALAPINS0UserAccount = [[NOVAGLOBALAPINS0UserAccount alloc] init];
  NS_DURING
  {
    [_nOVAGLOBALAPINS0UserAccount initWithReader: reader];
  }
  NS_HANDLER
  {
    _nOVAGLOBALAPINS0UserAccount = nil;
    [localException raise];
  }
  NS_ENDHANDLER

  [_nOVAGLOBALAPINS0UserAccount autorelease];
  return _nOVAGLOBALAPINS0UserAccount;
}

/**
 * Initialize this instance of NOVAGLOBALAPINS0UserAccount according to
 * the XML being read from the reader.
 *
 * @param reader The reader.
 */
- (id) initWithReader: (xmlTextReaderPtr) reader
{
  return [super initWithReader: reader];
}

/**
 * Write the XML for this instance of NOVAGLOBALAPINS0UserAccount to the writer.
 * Note that since we're only writing the XML type,
 * No start/end element will be written.
 *
 * @param reader The reader.
 */
- (void) writeXMLType: (xmlTextWriterPtr) writer
{
  [super writeXMLType:writer];
}

/**
 * Reads a NOVAGLOBALAPINS0UserAccount from an XML reader. The element to be read is
 * "userAccount".
 *
 * @param reader The XML reader.
 * @return The NOVAGLOBALAPINS0UserAccount.
 */
+ (id<JAXBElement>) readXMLElement: (xmlTextReaderPtr) reader {
  int status;
  NOVAGLOBALAPINS0UserAccount *_userAccount = nil;

  if (xmlTextReaderNodeType(reader) != XML_READER_TYPE_ELEMENT) {
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
    if (status < 1) {
      [NSException raise: @"XMLReadError"
                   format: @"Error advancing the reader to start element userAccount."];
    }
  }

  if (xmlStrcmp(BAD_CAST "userAccount", xmlTextReaderConstLocalName(reader)) == 0
      && xmlTextReaderConstNamespaceUri(reader) == NULL) {
#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read root element {}userAccount.");
#endif
    _userAccount = (NOVAGLOBALAPINS0UserAccount *)[NOVAGLOBALAPINS0UserAccount readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"Successfully read root element {}userAccount.");
#endif
  }
  else {
    if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
      [NSException raise: @"XMLReadError"
                   format: @"Unable to read NOVAGLOBALAPINS0UserAccount. Expected element userAccount. Current element: {}%s", xmlTextReaderConstLocalName(reader)];
    }
    else {
      [NSException raise: @"XMLReadError"
                   format: @"Unable to read NOVAGLOBALAPINS0UserAccount. Expected element userAccount. Current element: {%s}%s\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader)];
    }
  }

  return _userAccount;
}

/**
 * Writes this NOVAGLOBALAPINS0UserAccount to XML under element name "userAccount".
 * The namespace declarations for the element will be written.
 *
 * @param writer The XML writer.
 * @param _userAccount The UserAccount to write.
 * @return 1 if successful, 0 otherwise.
 */
- (void) writeXMLElement: (xmlTextWriterPtr) writer
{
  [self writeXMLElement: writer writeNamespaces: YES];
}

/**
 * Writes this NOVAGLOBALAPINS0UserAccount to an XML writer.
 *
 * @param writer The writer.
 * @param writeNs Whether to write the namespaces for this element to the xml writer.
 */
- (void) writeXMLElement: (xmlTextWriterPtr) writer writeNamespaces: (BOOL) writeNs
{
  int rc = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "userAccount", NULL);
  if (rc < 0) {
    [NSException raise: @"XMLWriteError"
                 format: @"Error writing start element {}userAccount. XML writer status: %i\n", rc];
  }

#if DEBUG_ENUNCIATE > 1
  NSLog(@"writing type {}userAccount for root element {}userAccount...");
#endif
  [self writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
  NSLog(@"successfully wrote type {}userAccount for root element {}userAccount...");
#endif
  rc = xmlTextWriterEndElement(writer);
  if (rc < 0) {
    [NSException raise: @"XMLWriteError"
                 format: @"Error writing end element {}userAccount. XML writer status: %i\n", rc];
  }
}

//documentation inherited.
- (BOOL) readJAXBAttribute: (xmlTextReaderPtr) reader
{
  void *_child_accessor;

  if ([super readJAXBAttribute: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (BOOL) readJAXBValue: (xmlTextReaderPtr) reader
{
  return [super readJAXBValue: reader];
}

//documentation inherited.
- (BOOL) readJAXBChildElement: (xmlTextReaderPtr) reader
{
  id __child;
  void *_child_accessor;
  int status, depth;

  if ([super readJAXBChildElement: reader]) {
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "firstName", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}firstName of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}firstName of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setFirstName: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "lastName", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}lastName of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}lastName of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setLastName: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "country", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}country of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}country of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setCountry: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "state", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}state of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}state of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setState: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "city", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}city of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}city of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setCity: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "email", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}email of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}email of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setEmail: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "password", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}password of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}password of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setPassword: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "roles", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}roles of type {}role.");
#endif

    _child_accessor = xmlTextReaderReadNOVAGLOBALAPINS0RoleType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    __child = [NSValue value: _child_accessor withObjCType: @encode(enum NOVAGLOBALAPINS0Role)];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}roles of type {}role.");
#endif

    if ([self roles]) {
      [self setRoles: [[self roles] arrayByAddingObject: __child]];
    }
    else {
      [self setRoles: [NSArray arrayWithObject: __child]];
    }
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "permissions", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}permissions of type {}permission.");
#endif

    _child_accessor = xmlTextReaderReadNOVAGLOBALAPINS0PermissionType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    __child = [NSValue value: _child_accessor withObjCType: @encode(enum NOVAGLOBALAPINS0Permission)];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}permissions of type {}permission.");
#endif

    if ([self permissions]) {
      [self setPermissions: [[self permissions] arrayByAddingObject: __child]];
    }
    else {
      [self setPermissions: [NSArray arrayWithObject: __child]];
    }
    return YES;
  } //end "if choice"


  return NO;
}

//documentation inherited.
- (int) readUnknownJAXBChildElement: (xmlTextReaderPtr) reader
{
  return [super readUnknownJAXBChildElement: reader];
}

//documentation inherited.
- (void) readUnknownJAXBAttribute: (xmlTextReaderPtr) reader
{
  [super readUnknownJAXBAttribute: reader];
}

//documentation inherited.
- (void) writeJAXBAttributes: (xmlTextWriterPtr) writer
{
  int status;

  [super writeJAXBAttributes: writer];

}

//documentation inherited.
- (void) writeJAXBValue: (xmlTextWriterPtr) writer
{
  [super writeJAXBValue: writer];
}

/**
 * Method for writing the child elements.
 *
 * @param writer The writer.
 */
- (void) writeJAXBChildElements: (xmlTextWriterPtr) writer
{
  int status;
  id __item;
  NSEnumerator *__enumerator;

  [super writeJAXBChildElements: writer];

  if ([self firstName]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "firstName", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}firstName."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}firstName...");
#endif
    [[self firstName] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}firstName...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}firstName."];
    }
  }
  if ([self lastName]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "lastName", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}lastName."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}lastName...");
#endif
    [[self lastName] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}lastName...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}lastName."];
    }
  }
  if ([self country]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "country", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}country."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}country...");
#endif
    [[self country] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}country...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}country."];
    }
  }
  if ([self state]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "state", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}state."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}state...");
#endif
    [[self state] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}state...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}state."];
    }
  }
  if ([self city]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "city", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}city."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}city...");
#endif
    [[self city] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}city...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}city."];
    }
  }
  if ([self email]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "email", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}email."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}email...");
#endif
    [[self email] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}email...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}email."];
    }
  }
  if ([self password]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "password", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}password."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}password...");
#endif
    [[self password] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}password...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}password."];
    }
  }
  if ([self roles]) {
    __enumerator = [[self roles] objectEnumerator];

    while ( (__item = [__enumerator nextObject]) ) {
      status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "roles", NULL);
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing start child element {}roles."];
      }

#if DEBUG_ENUNCIATE > 1
      NSLog(@"writing element {}roles...");
#endif
      status = xmlTextWriterWriteNOVAGLOBALAPINS0RoleType(writer, ((enum NOVAGLOBALAPINS0Role*) [((NSValue *)__item) pointerValue]));
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing child element {}roles."];
      }
#if DEBUG_ENUNCIATE > 1
      NSLog(@"successfully wrote element {}roles...");
#endif

      status = xmlTextWriterEndElement(writer);
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing end child element {}roles."];
      }
    } //end item iterator.
  }
  if ([self permissions]) {
    __enumerator = [[self permissions] objectEnumerator];

    while ( (__item = [__enumerator nextObject]) ) {
      status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "permissions", NULL);
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing start child element {}permissions."];
      }

#if DEBUG_ENUNCIATE > 1
      NSLog(@"writing element {}permissions...");
#endif
      status = xmlTextWriterWriteNOVAGLOBALAPINS0PermissionType(writer, ((enum NOVAGLOBALAPINS0Permission*) [((NSValue *)__item) pointerValue]));
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing child element {}permissions."];
      }
#if DEBUG_ENUNCIATE > 1
      NSLog(@"successfully wrote element {}permissions...");
#endif

      status = xmlTextWriterEndElement(writer);
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing end child element {}permissions."];
      }
    } //end item iterator.
  }
}
@end /* implementation NOVAGLOBALAPINS0UserAccount (JAXB) */

#endif /* DEF_NOVAGLOBALAPINS0UserAccount_M */
