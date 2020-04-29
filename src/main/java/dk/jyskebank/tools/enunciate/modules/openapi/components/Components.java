/*
 * Copyright © 2017-2018 Jyske Bank
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dk.jyskebank.tools.enunciate.modules.openapi.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.webcohesion.enunciate.EnunciateLogger;
import com.webcohesion.enunciate.api.datatype.DataType;
import com.webcohesion.enunciate.api.datatype.Namespace;
import com.webcohesion.enunciate.api.datatype.Syntax;

import dk.jyskebank.tools.enunciate.modules.openapi.ObjectTypeRenderer;

public class Components {
  private static final List<Schema> schemas = new ArrayList<>();

  private static Set<Syntax> syntaxes;

  public Components(EnunciateLogger logger, ObjectTypeRenderer objectTypeRenderer, Set<Syntax> syntaxes) {
    Components.syntaxes = syntaxes;
    for (Syntax syntax: syntaxes) {
      boolean syntaxIsJson = syntax.isAssignableToMediaType("application/json");
      for (Namespace namespace: syntax.getNamespaces()) {
        for (DataType datatype: namespace.getTypes()) {
          schemas.add(new Schema(logger, objectTypeRenderer, datatype, syntaxIsJson));
        }
      }
    }
  }

  public static Set<Syntax> getSyntaxes() {
    return syntaxes;
  }

  public boolean getIsEmpty() {
    return schemas.isEmpty();
  }

  public static boolean getIsEmptySt() {
    return schemas.isEmpty();
  }

  public List<Schema> getSchemas() {
    return schemas;
  }
  public static List<Schema> getSchemasSt() {
    return schemas;
  }
}
