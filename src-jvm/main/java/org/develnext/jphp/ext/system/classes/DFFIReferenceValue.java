package org.develnext.jphp.ext.system.classes;

import php.runtime.Memory;
import org.develnext.jphp.ext.system.DFFIExtension;
import php.runtime.annotation.Reflection;
import php.runtime.annotation.Reflection.Signature;
import php.runtime.env.Environment;
import php.runtime.lang.BaseObject;
import php.runtime.reflection.ClassEntity;

import com.sun.jna.ptr.*;
import com.sun.jna.*;

@Reflection.Name("DFFIReferenceValue")
@Reflection.Namespace(DFFIExtension.NS)
public class DFFIReferenceValue extends BaseObject {
	
	public ByReference refval = null;
	public Object _refval = null;
	public Class<?> type;

    public DFFIReferenceValue(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }
	
	@Signature
    public void __construct(Environment env, String _type) throws ClassNotFoundException {
		this.refval = Helper.ConvertToReference(_type);
        this.type = Helper.ConvertToJavaClassByType(_type);
    }
    
    @Signature
    public void __construct(Environment env, String _type, int _size) throws ClassNotFoundException {
		this._refval = Helper.ConvertToJavaArrayByType(_type, _size);
        this.type = Helper.ConvertToJavaClassByType(_type);
    }

	/*@Signature
    public void __construct(Environment env, String _type, Memory value) {
		this.type = _type;
		this.refval = Helper.ConvertObjectToReference(_type, Memory.unwrap(env, value));
    }*/
	
	@Signature
    public void setValue(Environment env, Memory value) {
		this.refval = Helper.SetValueToReference(this.refval, Memory.unwrap(env, value));
	}

    @Signature
    public Memory getValue() throws ClassNotFoundException {
        if(this.refval != null){
            return Helper.ConvertObjectToMemory(this.type, Helper.ConvertReferenceToObject(this.refval));
        }
        
        return null;
    }
    
    @Signature
    public String getNativeString(){
        if(this._refval != null){
            if(this.type == byte.class) return Native.toString((byte[])this._refval).trim();
            else if(this.type == char.class) return Native.toString((char[])this._refval).trim();
        }
        
        return null;
    }
    
    public boolean isReference(){
        if(this.refval == null) return false;
        
        return true;
    }
	
}