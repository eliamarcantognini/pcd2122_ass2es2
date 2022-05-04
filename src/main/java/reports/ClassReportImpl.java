package reports;

import reports.info.interfaces.FieldInfo;
import reports.info.interfaces.MethodInfo;
import reports.interfaces.ClassReport;

import java.util.LinkedList;
import java.util.List;

public class ClassReportImpl implements ClassReport {

    String fullClassName;
    String srcFullFileName;
    final List<MethodInfo> methodsInfo;
    final List<FieldInfo> fieldsInfo;

    public ClassReportImpl() {
        this.methodsInfo = new LinkedList<>();
        this.fieldsInfo = new LinkedList<>();
    }

    @Override
    public String getName() {
        return this.fullClassName;
    }

    @Override
    public String getSourceFullPath() {
        return this.srcFullFileName;
    }

    @Override
    public List<MethodInfo> getMethodsInfo() {
        return this.methodsInfo;
    }

    @Override
    public List<FieldInfo> getFieldsInfo() {
        return this.fieldsInfo;
    }

    @Override
    public void setName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    @Override
    public void setFullPath(String srcFullFileName) {
        this.srcFullFileName = srcFullFileName;
    }

    @Override
    public void addMethodInfo(MethodInfo methodInfo) {
        this.methodsInfo.add(methodInfo);
    }

    @Override
    public void addFieldInfo(FieldInfo fieldInfo) {
        this.fieldsInfo.add(fieldInfo);
    }

    @Override
    public String toString() {
        return "ClassReport:\n"
                + "\tClass Name: " + this.getName() + "\n"
                + "\tSource Full File Name: " + this.getSourceFullPath() + "\n"
                + "\tMethods Info: " + this.getMethodsInfo().toString() + "\n"
                + "\tFields Info: " + this.getFieldsInfo();
    }
}
