/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.2.3.v20140905
 * Generated at: 2016-07-05 08:09:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.base;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class json_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("file:/C:/Users/jjayaraj/.m2/repository/org/apache/struts/struts2-core/2.0.11.1/struts2-core-2.0.11.1.jar", Long.valueOf(1444973085364L));
    _jspx_dependants.put("jar:file:/C:/Users/jjayaraj/.m2/repository/org/apache/struts/struts2-core/2.0.11.1/struts2-core-2.0.11.1.jar!/META-INF/struts-tags.tld", Long.valueOf(1204516194000L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fbean_0026_005fname_005fid;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fparam_0026_005fvalue_005fname_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fs_005fbean_0026_005fname_005fid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fparam_0026_005fvalue_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fs_005fbean_0026_005fname_005fid.release();
    _005fjspx_005ftagPool_005fs_005fparam_0026_005fvalue_005fname_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_s_005fbean_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<!-- {{{ -->\r\n");
      out.write("<script>\r\n");
      out.write("\tvar form = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${jsonUtil.json}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(" ;\t\r\n");
      out.write("</script>\r\n");
      out.write("<!-- }}} -->");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_s_005fbean_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  s:bean
    org.apache.struts2.views.jsp.BeanTag _jspx_th_s_005fbean_005f0 = (org.apache.struts2.views.jsp.BeanTag) _005fjspx_005ftagPool_005fs_005fbean_0026_005fname_005fid.get(org.apache.struts2.views.jsp.BeanTag.class);
    _jspx_th_s_005fbean_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fbean_005f0.setParent(null);
    // /jsp/base/json.jsp(3,0) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fbean_005f0.setName("com.baseframework.biz.util.JSONConvertor");
    // /jsp/base/json.jsp(3,0) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fbean_005f0.setId("jsonUtil");
    int _jspx_eval_s_005fbean_005f0 = _jspx_th_s_005fbean_005f0.doStartTag();
    if (_jspx_eval_s_005fbean_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fbean_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fbean_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fbean_005f0.doInitBody();
      }
      do {
        out.write('\r');
        out.write('\n');
        out.write('	');
        if (_jspx_meth_s_005fparam_005f0(_jspx_th_s_005fbean_005f0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_s_005fbean_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fbean_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fbean_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fbean_0026_005fname_005fid.reuse(_jspx_th_s_005fbean_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fbean_0026_005fname_005fid.reuse(_jspx_th_s_005fbean_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fparam_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fbean_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  s:param
    org.apache.struts2.views.jsp.ParamTag _jspx_th_s_005fparam_005f0 = (org.apache.struts2.views.jsp.ParamTag) _005fjspx_005ftagPool_005fs_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.struts2.views.jsp.ParamTag.class);
    _jspx_th_s_005fparam_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fparam_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fbean_005f0);
    // /jsp/base/json.jsp(4,1) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fparam_005f0.setName("javaValue");
    // /jsp/base/json.jsp(4,1) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fparam_005f0.setValue("%{javaValue}");
    int _jspx_eval_s_005fparam_005f0 = _jspx_th_s_005fparam_005f0.doStartTag();
    if (_jspx_th_s_005fparam_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_s_005fparam_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_s_005fparam_005f0);
    return false;
  }
}
