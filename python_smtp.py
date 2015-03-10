#!/usr/bin/env python
#coding= utf8
from email import encoders
from email.header import Header
from email.mime.text import MIMEText
from email.utils import parseaddr, formataddr
import smtplib
from email.mime.base import MIMEBase
from email.mime.multipart import MIMEMultipart
import sys
def _format_addr(s):
	name, addr = parseaddr(s);
	return formataddr(( \
        Header(name, 'utf-8').encode(), \
        addr.encode('utf-8') if isinstance(addr, unicode) else addr))

from_addr = sys.argv[1]
password = sys.argv[2]
smtp_server = sys.argv[3]
s_smtp_port = int(sys.argv[4])
to_addr = sys.argv[5]
sub = sys.argv[6]

msg = MIMEMultipart()
msg['From'] = _format_addr(u'Aaron\'s Client<%s>' %from_addr)
msg['To'] = _format_addr(u'Admin <%s>' %to_addr)
msg['Subject'] = Header(sub, 'utf-8').encode()

msg.attach(MIMEText('<html><body><h1>SMTP Client</h1><p>by Aaron</p><p><b>This is a prototype version</b></p><p>Send by Java Python Client</p><p><img src="cid:0"></p><p><a href="https://github.com/AaronVon/Mail_Client">know more</a></p></body></html>',
                    'html', 'utf-8'))

with open('/Users/Aaron/Documents/me.jpg', 'rb') as f:
    mime = MIMEBase('image', 'jpg', filename='me.jpg')
    mime.add_header('Content-Disposition', 'attachment', filename='me.jpg')
    mime.add_header('Content-ID', '<0>')
    mime.add_header('X-Attachment-Id', '0')
    mime.set_payload(f.read())
    encoders.encode_base64(mime)
    msg.attach(mime)

server = smtplib.SMTP(smtp_server, s_smtp_port)
server.starttls()
server.set_debuglevel(1)
server.login(from_addr, password)
server.sendmail(from_addr, to_addr, msg.as_string())
server.quit()