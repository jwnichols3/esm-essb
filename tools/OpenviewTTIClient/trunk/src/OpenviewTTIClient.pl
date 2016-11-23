#!/apps/gentools/gnu/bin/perl
use Getopt::Long;
use LWP::Simple;
use LWP::UserAgent;


##
##  The following are arguments received from Openview
##
#   ARG[0] = Message ID
#   ARG[1] = Node Name
#   ARG[2] = Node Type
#   ARG[3] = Creation Date
#   ARG[4] = Creation Time
#   ARG[5] = Receive Date
#   ARG[6] = Receive Time
#   ARG[7] = Application
#   ARG[8] = Message Group
#   ARG[9] = Object
#   ARG[10] = Severity
#   ARG[11] = Operators
#   ARG[12] = Message
#   ARG[13] = Instruction
#   ARG[14] = Message Attributes
#   ARG[15] = Suppression Count

##
##  The URL to submit this event to
##
# my $url        = "http://localhost:9000/Openview2EEBBridge/ProcessEventTTI.do";
# my $url        = "http://localhost:7001/Openview2EEBBridge/ProcessEventTTI.do";
my $url        = "http://localhost:7001/Openview2EEBBridge/ProcessEventTTI.do";

#   INFO [http-9000-Processor25] (EventsFromNotifier.java:50) - Perl agent detected
#   INFO [http-9000-Processor24] (EventsFromTTI.java:36) - EventsFromTTI::retrieveMessage()
#   INFO [http-9000-Processor24] (EventsFromTTI.java:58) - TTI Event from Openview ( Messageid=6cae166a-1a76-71dc-0644-4534
#   661e0000, SourceNode=rdcuxsrv217.insidelive.net, SourceNodeType=Sun, CreationTime=(HTTPS), CreationDate=SPARC, ReceiveTi
#   me=05:54:44, ReceiveDate=06/14/2007, Application=06/15/2007, Object=SSM-no-app-def, Severity=unix, Operators=SSM-no-obj-
#   def-Logfile, Message=minor, Instruction=David_Nghiem, MessageAttributes=Kris, SuppressCount=alarmpoint )

##
##  Create an HTTP form to send to the Openview-EEB bridge
##
my $user_agent = LWP::UserAgent->new;
my $response   = $user_agent->post ( $ARGV[0],
        [
            MessageId       => $ARGV[ 1],
            NodeName        => $ARGV[ 2],
            NodeType        => $ARGV[ 3],
            CreationDate    => $ARGV[ 4],
            CreationTime    => $ARGV[ 5],
            ReceiveDate     => $ARGV[ 6],
            ReceiveTime     => $ARGV[ 7],
            Application     => $ARGV[ 8],
            MessageGroup    => $ARGV[ 9],
            Object          => $ARGV[10],
            Severity        => $ARGV[11],
            Operators       => $ARGV[12],
            Message         => $ARGV[13],
            Instruction     => $ARGV[14],
            Attributes      => $ARGV[15],
            SuppressCount   => $ARGV[16],
            OVOInterface    => "TTI",
            method          => "retrieveMessage"
        ]
    );

my $submit_results = $response->content;
print "$submit_results";

#perl OpenviewTTIClient.pl ${1} ${2} ${3} ${4} ${5} ${6} ${7} ${8} ${9} ${10} ${11} ${12} ${13} ${14} ${15} ${16}
