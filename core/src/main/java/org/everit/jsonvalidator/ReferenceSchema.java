/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.jsonvalidator;

public class ReferenceSchema extends Schema {

  public static class Builder extends Schema.Builder {

    private ReferenceSchema retval;

    @Override
    public ReferenceSchema build() {
      if (retval == null) {
        retval = new ReferenceSchema(this);
      }
      return retval;
    }

  }

  public static Builder builder() {
    return new Builder();
  }

  private Schema referredSchema;

  public ReferenceSchema(final Builder builder) {
    super(builder);
  }

  @Override
  void validate(final Object subject) {
    if (referredSchema == null) {
      throw new IllegalStateException("referredSchema must be injected before validation");
    }
    referredSchema.validate(subject);
  }

  public Schema getReferredSchema() {
    return referredSchema;
  }

  public void setReferredSchema(final Schema referredSchema) {
    if (this.referredSchema != null) {
      throw new IllegalStateException("referredSchema can be injected only once");
    }
    this.referredSchema = referredSchema;
  }

}
