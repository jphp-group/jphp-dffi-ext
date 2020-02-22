package org.develnext.jphp.ext.system;

import org.develnext.jphp.ext.system.classes.*;
import php.runtime.env.CompileScope;
import php.runtime.ext.support.Extension;

public class DFFIExtension extends Extension
{
  public static final String NS = "system";
  
  public DFFIExtension() {}
  
  public Extension.Status getStatus()
  {
    return Extension.Status.EXPERIMENTAL;
  }
  
  public String[] getPackageNames()
  {
    return new String[] { "system" };
  }
  
  public void onRegister(CompileScope scope)
  {
    registerClass(scope, DFFI.class);
    registerClass(scope, DFFIType.class);
    registerClass(scope, DFFIStruct.class);
    registerClass(scope, DFFIReferenceValue.class);
    registerClass(scope, DFFIConsole.class);
  }
}
