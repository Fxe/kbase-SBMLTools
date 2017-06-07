FROM kbase/kbase:sdkbase.latest
MAINTAINER KBase Developer
# -----------------------------------------
# In this section, you can install any system dependencies required
# to run your App.  For instance, you could place an apt-get update or
# install line here, a git checkout to download code, or run any other
# installation scripts.

# RUN apt-get update

# download a fasta reader/writer
RUN cd /kb/deployment/lib/jars \
    && wget https://downloads.sourceforge.net/project/jfasta/releases/jfasta-2.2.0/jfasta-2.2.0-jar-with-dependencies.jar

RUN cd /kb/deployment/lib/jars \
    && wget http://193.137.11.210/fliu/all-deps.zip && unzip all-deps.zip
# RUN mvn -v
RUN java -version

#unable to build java version
#RUN git clone https://github.com/spring-projects/spring-framework.git && cd spring-framework && ./gradlew install
#unable to build some wierd error
#RUN git clone https://github.com/Fxe/biosynth-framework.git && cd biosynth-framework && ./gradlew install
RUN pwd
RUN ls

# do git clone ?
# gradle install
# copy jars ?
    
# -----------------------------------------

COPY ./ /kb/module
RUN mkdir -p /kb/module/work
RUN chmod -R a+rw /kb/module

WORKDIR /kb/module

RUN make all

ENTRYPOINT [ "./scripts/entrypoint.sh" ]

CMD [ ]
