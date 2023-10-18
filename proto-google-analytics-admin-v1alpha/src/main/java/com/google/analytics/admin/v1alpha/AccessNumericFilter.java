/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/analytics/admin/v1alpha/access_report.proto

package com.google.analytics.admin.v1alpha;

/**
 *
 *
 * <pre>
 * Filters for numeric or date values.
 * </pre>
 *
 * Protobuf type {@code google.analytics.admin.v1alpha.AccessNumericFilter}
 */
public final class AccessNumericFilter extends com.google.protobuf.GeneratedMessageV3
    implements
    // @@protoc_insertion_point(message_implements:google.analytics.admin.v1alpha.AccessNumericFilter)
    AccessNumericFilterOrBuilder {
  private static final long serialVersionUID = 0L;
  // Use AccessNumericFilter.newBuilder() to construct.
  private AccessNumericFilter(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private AccessNumericFilter() {
    operation_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
    return new AccessNumericFilter();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }

  public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
    return com.google.analytics.admin.v1alpha.AccessReportProto
        .internal_static_google_analytics_admin_v1alpha_AccessNumericFilter_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.analytics.admin.v1alpha.AccessReportProto
        .internal_static_google_analytics_admin_v1alpha_AccessNumericFilter_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.analytics.admin.v1alpha.AccessNumericFilter.class,
            com.google.analytics.admin.v1alpha.AccessNumericFilter.Builder.class);
  }

  /**
   *
   *
   * <pre>
   * The operation applied to a numeric filter.
   * </pre>
   *
   * Protobuf enum {@code google.analytics.admin.v1alpha.AccessNumericFilter.Operation}
   */
  public enum Operation implements com.google.protobuf.ProtocolMessageEnum {
    /**
     *
     *
     * <pre>
     * Unspecified.
     * </pre>
     *
     * <code>OPERATION_UNSPECIFIED = 0;</code>
     */
    OPERATION_UNSPECIFIED(0),
    /**
     *
     *
     * <pre>
     * Equal
     * </pre>
     *
     * <code>EQUAL = 1;</code>
     */
    EQUAL(1),
    /**
     *
     *
     * <pre>
     * Less than
     * </pre>
     *
     * <code>LESS_THAN = 2;</code>
     */
    LESS_THAN(2),
    /**
     *
     *
     * <pre>
     * Less than or equal
     * </pre>
     *
     * <code>LESS_THAN_OR_EQUAL = 3;</code>
     */
    LESS_THAN_OR_EQUAL(3),
    /**
     *
     *
     * <pre>
     * Greater than
     * </pre>
     *
     * <code>GREATER_THAN = 4;</code>
     */
    GREATER_THAN(4),
    /**
     *
     *
     * <pre>
     * Greater than or equal
     * </pre>
     *
     * <code>GREATER_THAN_OR_EQUAL = 5;</code>
     */
    GREATER_THAN_OR_EQUAL(5),
    UNRECOGNIZED(-1),
    ;

    /**
     *
     *
     * <pre>
     * Unspecified.
     * </pre>
     *
     * <code>OPERATION_UNSPECIFIED = 0;</code>
     */
    public static final int OPERATION_UNSPECIFIED_VALUE = 0;
    /**
     *
     *
     * <pre>
     * Equal
     * </pre>
     *
     * <code>EQUAL = 1;</code>
     */
    public static final int EQUAL_VALUE = 1;
    /**
     *
     *
     * <pre>
     * Less than
     * </pre>
     *
     * <code>LESS_THAN = 2;</code>
     */
    public static final int LESS_THAN_VALUE = 2;
    /**
     *
     *
     * <pre>
     * Less than or equal
     * </pre>
     *
     * <code>LESS_THAN_OR_EQUAL = 3;</code>
     */
    public static final int LESS_THAN_OR_EQUAL_VALUE = 3;
    /**
     *
     *
     * <pre>
     * Greater than
     * </pre>
     *
     * <code>GREATER_THAN = 4;</code>
     */
    public static final int GREATER_THAN_VALUE = 4;
    /**
     *
     *
     * <pre>
     * Greater than or equal
     * </pre>
     *
     * <code>GREATER_THAN_OR_EQUAL = 5;</code>
     */
    public static final int GREATER_THAN_OR_EQUAL_VALUE = 5;

    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static Operation valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static Operation forNumber(int value) {
      switch (value) {
        case 0:
          return OPERATION_UNSPECIFIED;
        case 1:
          return EQUAL;
        case 2:
          return LESS_THAN;
        case 3:
          return LESS_THAN_OR_EQUAL;
        case 4:
          return GREATER_THAN;
        case 5:
          return GREATER_THAN_OR_EQUAL;
        default:
          return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<Operation> internalGetValueMap() {
      return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<Operation> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<Operation>() {
          public Operation findValueByNumber(int number) {
            return Operation.forNumber(number);
          }
        };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor getValueDescriptor() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalStateException(
            "Can't get the descriptor of an unrecognized enum value.");
      }
      return getDescriptor().getValues().get(ordinal());
    }

    public final com.google.protobuf.Descriptors.EnumDescriptor getDescriptorForType() {
      return getDescriptor();
    }

    public static final com.google.protobuf.Descriptors.EnumDescriptor getDescriptor() {
      return com.google.analytics.admin.v1alpha.AccessNumericFilter.getDescriptor()
          .getEnumTypes()
          .get(0);
    }

    private static final Operation[] VALUES = values();

    public static Operation valueOf(com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException("EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private Operation(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:google.analytics.admin.v1alpha.AccessNumericFilter.Operation)
  }

  public static final int OPERATION_FIELD_NUMBER = 1;
  private int operation_;
  /**
   *
   *
   * <pre>
   * The operation type for this filter.
   * </pre>
   *
   * <code>.google.analytics.admin.v1alpha.AccessNumericFilter.Operation operation = 1;</code>
   *
   * @return The enum numeric value on the wire for operation.
   */
  @java.lang.Override
  public int getOperationValue() {
    return operation_;
  }
  /**
   *
   *
   * <pre>
   * The operation type for this filter.
   * </pre>
   *
   * <code>.google.analytics.admin.v1alpha.AccessNumericFilter.Operation operation = 1;</code>
   *
   * @return The operation.
   */
  @java.lang.Override
  public com.google.analytics.admin.v1alpha.AccessNumericFilter.Operation getOperation() {
    @SuppressWarnings("deprecation")
    com.google.analytics.admin.v1alpha.AccessNumericFilter.Operation result =
        com.google.analytics.admin.v1alpha.AccessNumericFilter.Operation.valueOf(operation_);
    return result == null
        ? com.google.analytics.admin.v1alpha.AccessNumericFilter.Operation.UNRECOGNIZED
        : result;
  }

  public static final int VALUE_FIELD_NUMBER = 2;
  private com.google.analytics.admin.v1alpha.NumericValue value_;
  /**
   *
   *
   * <pre>
   * A numeric value or a date value.
   * </pre>
   *
   * <code>.google.analytics.admin.v1alpha.NumericValue value = 2;</code>
   *
   * @return Whether the value field is set.
   */
  @java.lang.Override
  public boolean hasValue() {
    return value_ != null;
  }
  /**
   *
   *
   * <pre>
   * A numeric value or a date value.
   * </pre>
   *
   * <code>.google.analytics.admin.v1alpha.NumericValue value = 2;</code>
   *
   * @return The value.
   */
  @java.lang.Override
  public com.google.analytics.admin.v1alpha.NumericValue getValue() {
    return value_ == null
        ? com.google.analytics.admin.v1alpha.NumericValue.getDefaultInstance()
        : value_;
  }
  /**
   *
   *
   * <pre>
   * A numeric value or a date value.
   * </pre>
   *
   * <code>.google.analytics.admin.v1alpha.NumericValue value = 2;</code>
   */
  @java.lang.Override
  public com.google.analytics.admin.v1alpha.NumericValueOrBuilder getValueOrBuilder() {
    return getValue();
  }

  private byte memoizedIsInitialized = -1;

  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
    if (operation_
        != com.google.analytics.admin.v1alpha.AccessNumericFilter.Operation.OPERATION_UNSPECIFIED
            .getNumber()) {
      output.writeEnum(1, operation_);
    }
    if (value_ != null) {
      output.writeMessage(2, getValue());
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (operation_
        != com.google.analytics.admin.v1alpha.AccessNumericFilter.Operation.OPERATION_UNSPECIFIED
            .getNumber()) {
      size += com.google.protobuf.CodedOutputStream.computeEnumSize(1, operation_);
    }
    if (value_ != null) {
      size += com.google.protobuf.CodedOutputStream.computeMessageSize(2, getValue());
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof com.google.analytics.admin.v1alpha.AccessNumericFilter)) {
      return super.equals(obj);
    }
    com.google.analytics.admin.v1alpha.AccessNumericFilter other =
        (com.google.analytics.admin.v1alpha.AccessNumericFilter) obj;

    if (operation_ != other.operation_) return false;
    if (hasValue() != other.hasValue()) return false;
    if (hasValue()) {
      if (!getValue().equals(other.getValue())) return false;
    }
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + OPERATION_FIELD_NUMBER;
    hash = (53 * hash) + operation_;
    if (hasValue()) {
      hash = (37 * hash) + VALUE_FIELD_NUMBER;
      hash = (53 * hash) + getValue().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.analytics.admin.v1alpha.AccessNumericFilter parseFrom(
      java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.analytics.admin.v1alpha.AccessNumericFilter parseFrom(
      java.nio.ByteBuffer data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.analytics.admin.v1alpha.AccessNumericFilter parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.analytics.admin.v1alpha.AccessNumericFilter parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.analytics.admin.v1alpha.AccessNumericFilter parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.analytics.admin.v1alpha.AccessNumericFilter parseFrom(
      byte[] data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.analytics.admin.v1alpha.AccessNumericFilter parseFrom(
      java.io.InputStream input) throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static com.google.analytics.admin.v1alpha.AccessNumericFilter parseFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static com.google.analytics.admin.v1alpha.AccessNumericFilter parseDelimitedFrom(
      java.io.InputStream input) throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }

  public static com.google.analytics.admin.v1alpha.AccessNumericFilter parseDelimitedFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static com.google.analytics.admin.v1alpha.AccessNumericFilter parseFrom(
      com.google.protobuf.CodedInputStream input) throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static com.google.analytics.admin.v1alpha.AccessNumericFilter parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
        PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() {
    return newBuilder();
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }

  public static Builder newBuilder(
      com.google.analytics.admin.v1alpha.AccessNumericFilter prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   *
   *
   * <pre>
   * Filters for numeric or date values.
   * </pre>
   *
   * Protobuf type {@code google.analytics.admin.v1alpha.AccessNumericFilter}
   */
  public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder>
      implements
      // @@protoc_insertion_point(builder_implements:google.analytics.admin.v1alpha.AccessNumericFilter)
      com.google.analytics.admin.v1alpha.AccessNumericFilterOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return com.google.analytics.admin.v1alpha.AccessReportProto
          .internal_static_google_analytics_admin_v1alpha_AccessNumericFilter_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.analytics.admin.v1alpha.AccessReportProto
          .internal_static_google_analytics_admin_v1alpha_AccessNumericFilter_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.analytics.admin.v1alpha.AccessNumericFilter.class,
              com.google.analytics.admin.v1alpha.AccessNumericFilter.Builder.class);
    }

    // Construct using com.google.analytics.admin.v1alpha.AccessNumericFilter.newBuilder()
    private Builder() {}

    private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
    }

    @java.lang.Override
    public Builder clear() {
      super.clear();
      operation_ = 0;

      if (valueBuilder_ == null) {
        value_ = null;
      } else {
        value_ = null;
        valueBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
      return com.google.analytics.admin.v1alpha.AccessReportProto
          .internal_static_google_analytics_admin_v1alpha_AccessNumericFilter_descriptor;
    }

    @java.lang.Override
    public com.google.analytics.admin.v1alpha.AccessNumericFilter getDefaultInstanceForType() {
      return com.google.analytics.admin.v1alpha.AccessNumericFilter.getDefaultInstance();
    }

    @java.lang.Override
    public com.google.analytics.admin.v1alpha.AccessNumericFilter build() {
      com.google.analytics.admin.v1alpha.AccessNumericFilter result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.google.analytics.admin.v1alpha.AccessNumericFilter buildPartial() {
      com.google.analytics.admin.v1alpha.AccessNumericFilter result =
          new com.google.analytics.admin.v1alpha.AccessNumericFilter(this);
      result.operation_ = operation_;
      if (valueBuilder_ == null) {
        result.value_ = value_;
      } else {
        result.value_ = valueBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }

    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
      return super.setField(field, value);
    }

    @java.lang.Override
    public Builder clearField(com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }

    @java.lang.Override
    public Builder clearOneof(com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }

    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field, int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }

    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.google.analytics.admin.v1alpha.AccessNumericFilter) {
        return mergeFrom((com.google.analytics.admin.v1alpha.AccessNumericFilter) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.analytics.admin.v1alpha.AccessNumericFilter other) {
      if (other == com.google.analytics.admin.v1alpha.AccessNumericFilter.getDefaultInstance())
        return this;
      if (other.operation_ != 0) {
        setOperationValue(other.getOperationValue());
      }
      if (other.hasValue()) {
        mergeValue(other.getValue());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8:
              {
                operation_ = input.readEnum();

                break;
              } // case 8
            case 18:
              {
                input.readMessage(getValueFieldBuilder().getBuilder(), extensionRegistry);

                break;
              } // case 18
            default:
              {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }

    private int operation_ = 0;
    /**
     *
     *
     * <pre>
     * The operation type for this filter.
     * </pre>
     *
     * <code>.google.analytics.admin.v1alpha.AccessNumericFilter.Operation operation = 1;</code>
     *
     * @return The enum numeric value on the wire for operation.
     */
    @java.lang.Override
    public int getOperationValue() {
      return operation_;
    }
    /**
     *
     *
     * <pre>
     * The operation type for this filter.
     * </pre>
     *
     * <code>.google.analytics.admin.v1alpha.AccessNumericFilter.Operation operation = 1;</code>
     *
     * @param value The enum numeric value on the wire for operation to set.
     * @return This builder for chaining.
     */
    public Builder setOperationValue(int value) {

      operation_ = value;
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * The operation type for this filter.
     * </pre>
     *
     * <code>.google.analytics.admin.v1alpha.AccessNumericFilter.Operation operation = 1;</code>
     *
     * @return The operation.
     */
    @java.lang.Override
    public com.google.analytics.admin.v1alpha.AccessNumericFilter.Operation getOperation() {
      @SuppressWarnings("deprecation")
      com.google.analytics.admin.v1alpha.AccessNumericFilter.Operation result =
          com.google.analytics.admin.v1alpha.AccessNumericFilter.Operation.valueOf(operation_);
      return result == null
          ? com.google.analytics.admin.v1alpha.AccessNumericFilter.Operation.UNRECOGNIZED
          : result;
    }
    /**
     *
     *
     * <pre>
     * The operation type for this filter.
     * </pre>
     *
     * <code>.google.analytics.admin.v1alpha.AccessNumericFilter.Operation operation = 1;</code>
     *
     * @param value The operation to set.
     * @return This builder for chaining.
     */
    public Builder setOperation(
        com.google.analytics.admin.v1alpha.AccessNumericFilter.Operation value) {
      if (value == null) {
        throw new NullPointerException();
      }

      operation_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * The operation type for this filter.
     * </pre>
     *
     * <code>.google.analytics.admin.v1alpha.AccessNumericFilter.Operation operation = 1;</code>
     *
     * @return This builder for chaining.
     */
    public Builder clearOperation() {

      operation_ = 0;
      onChanged();
      return this;
    }

    private com.google.analytics.admin.v1alpha.NumericValue value_;
    private com.google.protobuf.SingleFieldBuilderV3<
            com.google.analytics.admin.v1alpha.NumericValue,
            com.google.analytics.admin.v1alpha.NumericValue.Builder,
            com.google.analytics.admin.v1alpha.NumericValueOrBuilder>
        valueBuilder_;
    /**
     *
     *
     * <pre>
     * A numeric value or a date value.
     * </pre>
     *
     * <code>.google.analytics.admin.v1alpha.NumericValue value = 2;</code>
     *
     * @return Whether the value field is set.
     */
    public boolean hasValue() {
      return valueBuilder_ != null || value_ != null;
    }
    /**
     *
     *
     * <pre>
     * A numeric value or a date value.
     * </pre>
     *
     * <code>.google.analytics.admin.v1alpha.NumericValue value = 2;</code>
     *
     * @return The value.
     */
    public com.google.analytics.admin.v1alpha.NumericValue getValue() {
      if (valueBuilder_ == null) {
        return value_ == null
            ? com.google.analytics.admin.v1alpha.NumericValue.getDefaultInstance()
            : value_;
      } else {
        return valueBuilder_.getMessage();
      }
    }
    /**
     *
     *
     * <pre>
     * A numeric value or a date value.
     * </pre>
     *
     * <code>.google.analytics.admin.v1alpha.NumericValue value = 2;</code>
     */
    public Builder setValue(com.google.analytics.admin.v1alpha.NumericValue value) {
      if (valueBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        value_ = value;
        onChanged();
      } else {
        valueBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     *
     *
     * <pre>
     * A numeric value or a date value.
     * </pre>
     *
     * <code>.google.analytics.admin.v1alpha.NumericValue value = 2;</code>
     */
    public Builder setValue(
        com.google.analytics.admin.v1alpha.NumericValue.Builder builderForValue) {
      if (valueBuilder_ == null) {
        value_ = builderForValue.build();
        onChanged();
      } else {
        valueBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     *
     *
     * <pre>
     * A numeric value or a date value.
     * </pre>
     *
     * <code>.google.analytics.admin.v1alpha.NumericValue value = 2;</code>
     */
    public Builder mergeValue(com.google.analytics.admin.v1alpha.NumericValue value) {
      if (valueBuilder_ == null) {
        if (value_ != null) {
          value_ =
              com.google.analytics.admin.v1alpha.NumericValue.newBuilder(value_)
                  .mergeFrom(value)
                  .buildPartial();
        } else {
          value_ = value;
        }
        onChanged();
      } else {
        valueBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     *
     *
     * <pre>
     * A numeric value or a date value.
     * </pre>
     *
     * <code>.google.analytics.admin.v1alpha.NumericValue value = 2;</code>
     */
    public Builder clearValue() {
      if (valueBuilder_ == null) {
        value_ = null;
        onChanged();
      } else {
        value_ = null;
        valueBuilder_ = null;
      }

      return this;
    }
    /**
     *
     *
     * <pre>
     * A numeric value or a date value.
     * </pre>
     *
     * <code>.google.analytics.admin.v1alpha.NumericValue value = 2;</code>
     */
    public com.google.analytics.admin.v1alpha.NumericValue.Builder getValueBuilder() {

      onChanged();
      return getValueFieldBuilder().getBuilder();
    }
    /**
     *
     *
     * <pre>
     * A numeric value or a date value.
     * </pre>
     *
     * <code>.google.analytics.admin.v1alpha.NumericValue value = 2;</code>
     */
    public com.google.analytics.admin.v1alpha.NumericValueOrBuilder getValueOrBuilder() {
      if (valueBuilder_ != null) {
        return valueBuilder_.getMessageOrBuilder();
      } else {
        return value_ == null
            ? com.google.analytics.admin.v1alpha.NumericValue.getDefaultInstance()
            : value_;
      }
    }
    /**
     *
     *
     * <pre>
     * A numeric value or a date value.
     * </pre>
     *
     * <code>.google.analytics.admin.v1alpha.NumericValue value = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
            com.google.analytics.admin.v1alpha.NumericValue,
            com.google.analytics.admin.v1alpha.NumericValue.Builder,
            com.google.analytics.admin.v1alpha.NumericValueOrBuilder>
        getValueFieldBuilder() {
      if (valueBuilder_ == null) {
        valueBuilder_ =
            new com.google.protobuf.SingleFieldBuilderV3<
                com.google.analytics.admin.v1alpha.NumericValue,
                com.google.analytics.admin.v1alpha.NumericValue.Builder,
                com.google.analytics.admin.v1alpha.NumericValueOrBuilder>(
                getValue(), getParentForChildren(), isClean());
        value_ = null;
      }
      return valueBuilder_;
    }

    @java.lang.Override
    public final Builder setUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }

    // @@protoc_insertion_point(builder_scope:google.analytics.admin.v1alpha.AccessNumericFilter)
  }

  // @@protoc_insertion_point(class_scope:google.analytics.admin.v1alpha.AccessNumericFilter)
  private static final com.google.analytics.admin.v1alpha.AccessNumericFilter DEFAULT_INSTANCE;

  static {
    DEFAULT_INSTANCE = new com.google.analytics.admin.v1alpha.AccessNumericFilter();
  }

  public static com.google.analytics.admin.v1alpha.AccessNumericFilter getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AccessNumericFilter> PARSER =
      new com.google.protobuf.AbstractParser<AccessNumericFilter>() {
        @java.lang.Override
        public AccessNumericFilter parsePartialFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
          Builder builder = newBuilder();
          try {
            builder.mergeFrom(input, extensionRegistry);
          } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(builder.buildPartial());
          } catch (com.google.protobuf.UninitializedMessageException e) {
            throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
          } catch (java.io.IOException e) {
            throw new com.google.protobuf.InvalidProtocolBufferException(e)
                .setUnfinishedMessage(builder.buildPartial());
          }
          return builder.buildPartial();
        }
      };

  public static com.google.protobuf.Parser<AccessNumericFilter> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AccessNumericFilter> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.analytics.admin.v1alpha.AccessNumericFilter getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
